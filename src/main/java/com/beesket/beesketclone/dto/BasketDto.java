package com.beesket.beesketclone.dto;

import com.beesket.beesketclone.model.Product;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BasketDto {
    private Product product;
    private int count;
}
