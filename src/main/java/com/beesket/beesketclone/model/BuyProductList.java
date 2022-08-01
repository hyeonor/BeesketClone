package com.beesket.beesketclone.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class BuyProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false)
    private int count;

    @ManyToOne
    @JoinColumn(name = "basketId")
    private Basket basket;

}
