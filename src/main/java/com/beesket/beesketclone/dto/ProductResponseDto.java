package com.beesket.beesketclone.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductResponseDto {
    private List<ProductInformationDto> productInfo;
    private int totalCount;
}
