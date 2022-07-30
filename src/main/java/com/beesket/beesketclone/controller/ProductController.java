package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.ProductResponseDto;
import com.beesket.beesketclone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{categoryId}/product")
    public ResponseEntity<ProductResponseDto> getProducts(@PathVariable String categoryId, @RequestParam("page") int page){
        ProductResponseDto products = productService.showProduct(categoryId, page - 1);
        return ResponseEntity.ok().body(products);
    }
}
