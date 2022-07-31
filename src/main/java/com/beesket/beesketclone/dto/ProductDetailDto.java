package com.beesket.beesketclone.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDetailDto {
    private String imgUrl;
    private String productName;
    private int price;
    private String productDetail;

    public ProductDetailDto(String imgUrl, String productName, int price, String productDetail) {
        this.imgUrl = imgUrl;
        this.productName = productName;
        this.price = price;
        this.productDetail = productDetail;
    }
}
