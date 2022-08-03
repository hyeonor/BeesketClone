package com.beesket.beesketclone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequestDto {
    private Long productId;
    private int count;
}
