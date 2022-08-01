package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.CommentRequestDto;
import com.beesket.beesketclone.model.Comment;
import com.beesket.beesketclone.model.Product;
import com.beesket.beesketclone.model.User;
import com.beesket.beesketclone.repository.CommentRepository;
import com.beesket.beesketclone.repository.ProductRepository;
import com.beesket.beesketclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
