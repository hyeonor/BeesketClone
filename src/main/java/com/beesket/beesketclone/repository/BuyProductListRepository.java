package com.beesket.beesketclone.repository;

import com.beesket.beesketclone.model.Basket;
import com.beesket.beesketclone.model.BuyProductList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuyProductListRepository extends JpaRepository<BuyProductList, Long> {
//    List<BuyProductList> findByUser_Id(Long userId);
    Optional<BuyProductList> findByProduct_IdAndUser_Id(Long productId, Long userId);

    List<BuyProductList> findByBasket(Basket basket);
}

