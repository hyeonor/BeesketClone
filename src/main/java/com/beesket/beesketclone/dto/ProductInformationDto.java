package com.beesket.beesketclone.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInformationDto {
    private String imgUrl;
    private String productName;
    private int price;

    public ProductInformationDto(String imgUrl, String productName, int price) {
        this.imgUrl = imgUrl;
        this.productName = productName;
        this.price = price;
    }
}