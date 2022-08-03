package com.beesket.beesketclone.dto;

import com.beesket.beesketclone.model.Basket;
import com.beesket.beesketclone.model.BuyProductList;
import com.beesket.beesketclone.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BasketResponseDto {
    private Long basketId;
    private List<BuyProductList> buyProductList;
    private int deliveryFee;

    public BasketResponseDto(Basket basket){
        this.basketId = basket.getId();
        this.buyProductList = basket.getBuyProductList();
        this.deliveryFee = basket.getDeliveryFee();
    }
}
