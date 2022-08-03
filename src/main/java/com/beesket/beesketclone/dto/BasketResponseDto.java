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
    private User user;
    private List<BasketDto> buyProductList;
    private int deliveryFee;
    private int sumPrice;

    public BasketResponseDto(Basket basket){
        this.setBasketId(basket.getId());
        this.setUser(basket.getUser());
        this.setDeliveryFee(basket.getDeliveryFee());
        this.setSumPrice(basket.getSumPrice());
        List<BasketDto> basketDtoList = new ArrayList<>();
        for(BuyProductList buyProductList : basket.getBuyProductList()){
            BasketDto basketDto = BasketDto.builder()
                    .product(buyProductList.getProduct())
                    .deliveryFee(buyProductList.getBasket().getDeliveryFee())
                    .build();
            basketDtoList.add(basketDto);
        }
        this.setBuyProductList(basketDtoList);
    }

}
