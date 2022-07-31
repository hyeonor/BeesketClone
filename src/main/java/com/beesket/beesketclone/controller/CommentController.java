package com.beesket.beesketclone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CommentController {
    @PostMapping("/product/{productId}/comment")
    public ResponseEntity<Void> saveComment(@PathVariable Long productId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto commentRequestDto) {
        String email = userDetails.getEmail();
        commentService.saveComment(email, productId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
