package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Image;
import com.beesket.beesketclone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<String> findById(Product productId);
}
