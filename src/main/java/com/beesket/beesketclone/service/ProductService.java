package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.ProductInformationDto;
import com.beesket.beesketclone.dto.ProductResponseDto;
import com.beesket.beesketclone.model.Product;
import com.beesket.beesketclone.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto showProduct(int categoryId) {
        long totalCount;
        List<Product> products;
        ProductResponseDto productResponseDtos = new ProductResponseDto();
        List<ProductInformationDto> ProductInformationDtoList = new ArrayList<>();

        if(categoryId == 0){ //All 카테고리
            totalCount = productRepository.count();//제품 전체 개수
            products = productRepository.findAll();//제품 전체 불어오기

            for(Product product : products){
                ProductInformationDto productInformationDto = new ProductInformationDto(
                        product.getImgUrl(),
                        product.getProductName(),
                        product.getPrice());

                ProductInformationDtoList.add(productInformationDto);
            }
        } else {
            totalCount = productRepository.countAllByCategoryId(categoryId);//카테코리마다 제품 전체 개수
            products = productRepository.findAllByCategoryId(categoryId);

            for(Product product : products){
                ProductInformationDto productInformationDto = new ProductInformationDto(
                        product.getImgUrl(),
                        product.getProductName(),
                        product.getPrice());

                ProductInformationDtoList.add(productInformationDto);
            }
        }

        productResponseDtos.setProductInfo(ProductInformationDtoList);
        productResponseDtos.setTotalCount((int)totalCount);

        return productResponseDtos;
    }
}
