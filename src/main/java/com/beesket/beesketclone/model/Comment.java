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
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id를 자동 생성
    @Column(name = "comment_id")
    private Long commentId;

    @Column
    private String content;

    @Column
    private int scope;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Comment(User user, String content, Product product, int scope) {
        this.user = user;
        this.content = content;
        this.product = product;
        this.scope = scope;
    }

    public void addCommentToPost(Product Product) {
        Product.addComment(this);
    }

    // Comment 생성
    public static Comment createComment(User user, String content, Product product, int scope) {
        Comment comment = new Comment(user, content, product, scope);
        comment.addCommentToPost(product);
        return comment;
    }
}