package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.CommentRequestDto;
import com.beesket.beesketclone.dto.CommentResponseDto;
import com.beesket.beesketclone.exception.CustomException;
import com.beesket.beesketclone.exception.ErrorCode;
import com.beesket.beesketclone.model.Comment;
import com.beesket.beesketclone.model.Product;
import com.beesket.beesketclone.model.User;
import com.beesket.beesketclone.repository.CommentRepository;
import com.beesket.beesketclone.repository.ProductRepository;
import com.beesket.beesketclone.repository.UserRepository;
import com.beesket.beesketclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveComment(String email, Long productId, CommentRequestDto commentRequestDto) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글을 찾을 수 없습니다.")
        );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("다시 로그인해 주세요."));

        String content = commentRequestDto.getContent();
        int scope = commentRequestDto.getScope();

        commentRepository.save(Comment.createComment(user, content, product, scope));
    }

    @Transactional
    public void deleteComment(Long commentId, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 댓글을 찾을 수 없습니다."));

        Long userId = userDetails.getUser().getId();
        if(userId.equals(comment.getUser().getId())) {
            commentRepository.deleteById(commentId);
        }else {
            throw new CustomException(ErrorCode.COMMENT_LOGIN_CHECK_CODE);
        }
    }

    public List<CommentResponseDto> listComment(Long productId) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        List<Comment> commentList = commentRepository.findAllByProductIdOrderByCreatedAtDesc(productId);

        for (Comment comment : commentList) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }
        return commentResponseDtoList;
    }
}
