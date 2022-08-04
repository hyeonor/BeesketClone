package com.beesket.beesketclone.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductResponseDto {
    private List<ProductInformationDto> productInfo; //제품 정보
    private int totalCount; // 카테고리 별 제품 전체 개수
}
