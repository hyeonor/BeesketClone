package com.beesket.beesketclone.dto;

import com.beesket.beesketclone.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductInformationDto {
    private Long id;
    private List<String> imageList;
    private String productName;
    private int price;

    public ProductInformationDto(Product product, List<String> imageDtoList) {
        this.id = product.getId();
        this.imageList = imageDtoList;
        this.productName = product.getProductName();
        this.price = product.getPrice();
    }
}