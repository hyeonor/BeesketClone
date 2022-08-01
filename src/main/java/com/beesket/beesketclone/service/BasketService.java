package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.BasketRequestDto;
import com.beesket.beesketclone.model.BuyProductList;
import com.beesket.beesketclone.model.Product;
import com.beesket.beesketclone.model.User;
import com.beesket.beesketclone.repository.BasketRepository;
import com.beesket.beesketclone.repository.BuyProductListRepository;
import com.beesket.beesketclone.repository.ProductRepository;
import com.beesket.beesketclone.repository.UserRepository;
import com.beesket.beesketclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final BuyProductListRepository buyProductListRepository;

    //장바구니 담기
    @Transactional
    public void saveBasket(BasketRequestDto basketRequestDto, UserDetailsImpl userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("회원이 존재하지 않습니다.")
        );

        Product product = productRepository.findById(basketRequestDto.getProductId()).orElseThrow(
                () -> new IllegalArgumentException("상품이 존재하지 않습니다.")
        );

        Optional<BuyProductList> find = buyProductListRepository.findByProduct_IdAndUser_Id(basketRequestDto.getProductId(),user.getId());

        if (find.isPresent()){
            find.get().setCount(basketRequestDto.getCount()+find.get().getCount());
        } else {
            BuyProductList buyProductList = BuyProductList.builder()
                    .user(user)
                    .product(product)
                    .email(user.getEmail())
                    .count(basketRequestDto.getCount())
                    .build();

            buyProductListRepository.save(buyProductList);
        }

    }

//    public List<BasketResponseDto> showBasket(UserDetailsImpl userDetails) {
//
//        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
//                () -> new IllegalArgumentException("회원이 존재하지 않습니다.")
//        );
//
//    }

}
