package com.beesket.beesketclone.dto;

import com.beesket.beesketclone.model.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInformationDto {
    private Long id;
    private String imgUrl;
    private String productName;
    private int price;

    public ProductInformationDto(Product product) {
        this.id = product.getId();
        this.imgUrl = product.getImgUrl();
        this.productName = product.getProductName();
        this.price = product.getPrice();
    }
}