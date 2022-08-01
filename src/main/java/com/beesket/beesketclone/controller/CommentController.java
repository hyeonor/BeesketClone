package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.CommentRequestDto;
import com.beesket.beesketclone.security.UserDetailsImpl;
import com.beesket.beesketclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(null);
    }
}
