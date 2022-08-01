package com.beesket.beesketclone.dto;

public class BasketProductDto {
    private String productName;
    private int count;
    private int price;

    public BasketProductDto(String productName, int count, int price){
        this.productName = productName;
        this.count = count;
        this.price = price;
    }
}
