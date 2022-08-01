package com.beesket.beesketclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponseDto {
    private List<BasketProductDto> basketProduct;
    private int deliveryFee;
    private int totalPrice;

}
