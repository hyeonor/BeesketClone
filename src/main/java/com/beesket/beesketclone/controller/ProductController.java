package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.ProductDetailDto;
import com.beesket.beesketclone.dto.ProductResponseDto;
import com.beesket.beesketclone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    //제품 페이지 메인 페이지 조회
    @GetMapping("/product")
    public ResponseEntity<ProductResponseDto> getProducts(@RequestParam("categoryId") String categoryId, @RequestParam("page") int page){
        ProductResponseDto products = productService.showProduct(categoryId, page - 1);
        return ResponseEntity.ok().body(products);
    }

    //제품 상세 조회
    @GetMapping("/product/detail")
    public ResponseEntity<ProductDetailDto> getProductDetail(@RequestParam("productId") Long productId) {
        ProductDetailDto product = productService.showProductDetail(productId);
        return ResponseEntity.ok().body(product);
    }
}
