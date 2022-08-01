package com.beesket.beesketclone.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BasketResponseDto {
    private List<BasketProductDto> basketProduct;
    private int deliveryFee;
    private int totalPrice;

}
