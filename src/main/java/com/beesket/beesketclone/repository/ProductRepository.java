package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    long countAllByCategoryName(String categoryName);
    Page<Product> findAllByCategoryName(String categoryName, Pageable pageable);

    Product findAllById(Long productId);
}
