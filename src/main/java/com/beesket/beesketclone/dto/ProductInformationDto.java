package com.beesket.beesketclone.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInformationDto {
    private Long id;
    private String imgUrl;
    private String productName;
    private int price;

    public ProductInformationDto(Long id, String imgUrl, String productName, int price) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.productName = productName;
        this.price = price;
    }
}