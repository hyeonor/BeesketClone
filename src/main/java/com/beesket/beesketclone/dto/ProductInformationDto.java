package com.beesket.beesketclone.dto;

import com.beesket.beesketclone.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductInformationDto {
    private Long id;
    private List<String> imageList; //이미지 리스트
    private String productName; //제품 내용
    private int price; //제품 가격

    public ProductInformationDto(Product product, List<String> imageDtoList) {
        this.id = product.getId();
        this.imageList = imageDtoList;
        this.productName = product.getProductName();
        this.price = product.getPrice();
    }
}