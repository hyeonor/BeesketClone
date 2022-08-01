package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Basket;
import com.beesket.beesketclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
//    List<Basket> findAllByIdAndProductId(Long id, Long productId);
//
//    List<Basket> findAllByUserId(Long userId);
}
