package com.beesket.beesketclone.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
public class BasketRequestDto {
    private String email;
    private Long productId;
    private int count;

    public BasketRequestDto(String email, Long productId, int count){
        this.email = email;
        this.productId = productId;
        this.count = count;
    }
}
