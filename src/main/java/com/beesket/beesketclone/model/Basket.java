package com.beesket.beesketclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "basket")
    private List<BasketList> basketList;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    public Basket()

}
