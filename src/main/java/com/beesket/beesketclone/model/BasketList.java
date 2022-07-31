package com.beesket.beesketclone.model;

import javax.persistence.*;

public class BasketList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productListId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Column(nullable = false)
    private int count;
}
