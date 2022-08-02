package com.beesket.beesketclone.dto;

import com.beesket.beesketclone.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductDetailDto {
    private List<String> imgUrl;
    private String productName;
    private int price;
    private String productDetail;

    public ProductDetailDto(Product product, List<String> imageDtoList) {
        this.imgUrl = imageDtoList;
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.productDetail = product.getProductDetail();
    }
}
