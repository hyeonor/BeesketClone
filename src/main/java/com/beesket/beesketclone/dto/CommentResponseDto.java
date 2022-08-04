package com.beesket.beesketclone.dto;

import com.beesket.beesketclone.model.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentId; //구매평 Id
    private int scope; //별점
    private String content; //구매평
    private LocalDateTime createdAt; //생성시간

    @JsonProperty(value = "writer")
    private CommentWriterDto commentWriterDto; //작성자

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentWriterDto = new CommentWriterDto(comment.getUser().getEmail());
        this.scope = comment.getScope();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

    @Getter
    @AllArgsConstructor
    static class CommentWriterDto {
        private String username;
    }
}
