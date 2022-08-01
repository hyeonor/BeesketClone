package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.BasketRequestDto;
import com.beesket.beesketclone.repository.BasketRepository;
import com.beesket.beesketclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;

    //구매 목록
    @Transactional
    public void saveBasket(BasketRequestDto basketRequestDto, UserDetailsImpl userDetails) {


        User user = userRepository.findById(userDetails.getEmail()).orElseThrow(
                () -> new NullPointerException("회원이 존재하지 않습니다.")
        );

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NullPointerException("상품이 존재하지 않습니다.")
        );



    }


}
