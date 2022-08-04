package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.CommentRequestDto;
import com.beesket.beesketclone.dto.CommentResponseDto;
import com.beesket.beesketclone.security.UserDetailsImpl;
import com.beesket.beesketclone.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    // 댓글작성
    @PostMapping("product/{productId}/comment")
    public ResponseEntity<Void> saveComment(@PathVariable Long productId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        String email = userDetails.getUsername();
        commentService.saveComment(email, productId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // 댓글리스트 조회
    @GetMapping("product/{productId}/comment")
    public ResponseEntity<CommentWrapper<List<CommentResponseDto>>> listComment(@PathVariable Long productId) {
        List<CommentResponseDto> commentList = commentService.listComment(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new CommentWrapper<>(commentList));
    }

    //댓글 삭제
    @DeleteMapping("product/{commentId}/comment")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제 완료");
    }

    @Data
    @AllArgsConstructor
    static class CommentWrapper<T> {
        private T comments;
    }
}
