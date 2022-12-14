package com.beesket.beesketclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private String imgUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "basketId")
    private Basket basket;

}
