package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(int categoryId);
    long countAllByCategoryId(int categoryId);
}
