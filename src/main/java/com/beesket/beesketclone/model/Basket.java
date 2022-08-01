package com.beesket.beesketclone.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Basket extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "productId")
//    private Product product;

    @OneToMany(mappedBy = "basket")
    private List<BuyProductList> buyProductList;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int sumPrice;

    @Column(nullable = false)
    private int totalPrice;

}
