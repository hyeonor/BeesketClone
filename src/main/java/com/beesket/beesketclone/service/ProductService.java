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

    //제품 메인 페이지 조회
    public ProductResponseDto showProduct(String categoryName, int page) {
        long totalCount;
        Page<Product> products;
        ProductResponseDto productResponseDto = new ProductResponseDto();
        List<ProductInformationDto> ProductInformationDtoList = new ArrayList<>();


        Sort sort = Sort.by("id");//productId로 오름차순으로 정렬
        Pageable pageable = PageRequest.of(page, 15, sort);//한 페이지당 상품 15개, productId로 정렬

        if(categoryName.equals("ALL")) { //All 카테고리
            totalCount = productRepository.count();//제품 전체 개수
            products = productRepository.findAll(pageable);//제품 전체 불어오기

            getProduct(products, ProductInformationDtoList); //제품 정보 가져오기

        } else {
            totalCount = productRepository.countAllByCategoryName(categoryName);//카테코리별 제품 전체 개수
            products = productRepository.findAllByCategoryName(categoryName, pageable);//카테코리별 제품 불어오기

            getProduct(products, ProductInformationDtoList);//제품 정보 가져오기
        }
        productResponseDto.setProductInfo(ProductInformationDtoList);
        productResponseDto.setTotalCount((int)totalCount);

        return productResponseDto;
    }

    //제품 정보 가져오기
    private void getProduct(Page<Product> products, List<ProductInformationDto> ProductInformationDtoList) {

        for(Product product : products) {
            Long productId = product.getId();
            List<Image> images = imageRepository.findAllByProductId(productId);
            List<String> imageDtoList = new ArrayList<>();

            for (Image image : images) {
                imageDtoList.add(image.getImgUrl());
            }
            ProductInformationDto productInformationDto = new ProductInformationDto(product, imageDtoList);
            ProductInformationDtoList.add(productInformationDto);
        }
    }

    //상세 페이지 조회
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
