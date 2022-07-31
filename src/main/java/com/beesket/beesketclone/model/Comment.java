package com.beesket.beesketclone.model;

import javax.persistence.*;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int scope;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Comment(User user, Product product, int scope, String comment) {
        this.user = user;
        this.product = product;
        this.scope = scope;
        this.comment = comment;
    }
}
