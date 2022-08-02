package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.ProductDetailDto;
import com.beesket.beesketclone.dto.ProductInformationDto;
import com.beesket.beesketclone.dto.ProductResponseDto;
import com.beesket.beesketclone.model.Image;
import com.beesket.beesketclone.model.Product;
import com.beesket.beesketclone.repository.ImageRepository;
import com.beesket.beesketclone.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public ProductResponseDto showProduct(String categoryName, int page) {
        long totalCount;
        Long productId;
        Page<Product> products;
        ProductResponseDto productResponseDto = new ProductResponseDto();
        List<ProductInformationDto> ProductInformationDtoList = new ArrayList<>();


        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(page, 15, sort);

        if(categoryName.equals("ALL")) { //All 카테고리
            totalCount = productRepository.count();//제품 전체 개수
            products = productRepository.findAll(pageable);//제품 전체 불어오기

            for(Product product : products) {
                productId = product.getId();
                List<Image> images = imageRepository.findAllByProductId(productId);
                List<String> imageDtoList = new ArrayList<>();

                for (Image image : images) {
                    imageDtoList.add(image.getImgUrl());
                }
                ProductInformationDto productInformationDto = new ProductInformationDto(product, imageDtoList);
                ProductInformationDtoList.add(productInformationDto);
            }
        } else {
            totalCount = productRepository.countAllByCategoryName(categoryName);//카테코리별 제품 전체 개수
            products = productRepository.findAllByCategoryName(categoryName, pageable);//카테코리별 제품 불어오기

            for(Product product : products){
                productId = product.getId();
                List<Image> images = imageRepository.findAllByProductId(productId);
                List<String> imageDtoList = new ArrayList<>();

                for (Image image : images) {
                    imageDtoList.add(image.getImgUrl());
                }

                ProductInformationDto productInformationDto = new ProductInformationDto(product, imageDtoList);
                ProductInformationDtoList.add(productInformationDto);
            }
        }
        productResponseDto.setProductInfo(ProductInformationDtoList);
        productResponseDto.setTotalCount((int)totalCount);

        return productResponseDto;
    }

    public ProductDetailDto showProductDetail(Long productId) {
        Product product = productRepository.findAllById(productId);

        List<Image> images = imageRepository.findAllByProductId(productId);
        List<String> imageDtoList = new ArrayList<>();

        for (Image image : images) {
            imageDtoList.add(image.getImgUrl());
        }

        return new ProductDetailDto(product, imageDtoList);
    }
}
