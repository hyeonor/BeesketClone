package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Basket;
import com.beesket.beesketclone.model.BuyProductList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyProductListRepository extends JpaRepository<BuyProductList, Long> {
    BuyProductList findByProduct_IdAndBasket(Long productId, Basket basket);
    List<BuyProductList> findByBasket(Basket basket);
}

