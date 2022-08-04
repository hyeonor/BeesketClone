package com.beesket.beesketclone.dto;

import com.beesket.beesketclone.model.Basket;
import com.beesket.beesketclone.model.BuyProductList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketResponseDto {
    private Long basketId;
    private List<BuyProductList> buyProductList;
    private int deliveryFee;

    public BasketResponseDto(Basket basket){
        this.setBasketId(basket.getId());
        this.setDeliveryFee(basket.getDeliveryFee());
        this.setBuyProductList(basket.getBuyProductList());
    }
}
