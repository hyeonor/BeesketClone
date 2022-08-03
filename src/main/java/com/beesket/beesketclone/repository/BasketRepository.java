package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByUser_Id(Long userId);
}
