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

    private Long commentId;
    private int scope;
    private String content;

    private LocalDateTime createdAt;
    @JsonProperty(value = "writer")
    private CommentWriterDto commentWriterDto;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentWriterDto = new CommentWriterDto(
                comment.getUser().getEmail());
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
