package com.beesket.beesketclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BasketRequestDto {
    private String email;
    private Long productId;
    private int count;
}
