package com.beesket.beesketclone.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id를 자동 생성
    @Column(name = "comment_id")
    private Long commentId;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Comment(User user, String content, Product product) {
        this.user = user;
        this.product = product;
        this.content = content;
    }

    public void addCommentToPost(Product Product) {
        Product.addComment(this);
    }

    // Comment 생성
    public static Comment createComment(User user, String content, Product post) {
        Comment comment = new Comment(user, content, post);
        comment.addCommentToPost(post);
        return comment;
    }
}