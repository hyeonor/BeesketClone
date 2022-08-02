package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.BasketProductDto;
import com.beesket.beesketclone.dto.BasketResponseDto;
import com.beesket.beesketclone.model.Basket;
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
    public void saveBasket(BasketProductDto basketProductDto, UserDetailsImpl userDetails) {

        Basket basket = basketRepository.findByUser_Id(userDetails.getUser().getId()); //나중에 예외처리 하기

        if(basket == null){
            basket = Basket.builder()
                    .user(userDetails.getUser())
                    .buyProductList(null)
                    .count(0)
                    .deliveryFee(0)
                    .sumPrice(0)
                    .build();

            basketRepository.save(basket);
        }

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("회원이 존재하지 않습니다.")
        );

        Product product = productRepository.findById(basketProductDto.getProductId()).orElseThrow(
                () -> new IllegalArgumentException("상품이 존재하지 않습니다.")
        );

        Optional<BuyProductList> find = buyProductListRepository.findByProduct_IdAndUser_Id(basketProductDto.getProductId(),user.getId());

        if (find.isPresent()){
            find.get().setCount(basketProductDto.getCount()+find.get().getCount());
        } else {
            BuyProductList buyProductList = BuyProductList.builder()
                    .basket(basket)
                    .product(product)
//                    .email(user.getEmail())
                    .count(basketProductDto.getCount())
                    .build();

            buyProductListRepository.save(buyProductList);
        }

    }

    //장바구니 조회
    public BasketResponseDto basketList(UserDetailsImpl userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원이 존재하지 않습니다.")
        );

        Basket basket = basketRepository.findByUser_Id(userDetails.getUser().getId());

        List<BuyProductList> buyProductList = buyProductListRepository.findByBasket(basket);

        int deliverFee = 0;
        int sumPrice = 0;
        int allCount = 0;

        for (BuyProductList list : buyProductList) {
            int price = list.getProduct().getPrice();
            int count = list.getCount();
            allCount += count;
            int sum = price * count;
            sumPrice += sum;
        }

        if (sumPrice < 70000) {
            deliverFee += 3000;
        }

//        basket = Basket.builder()
//                .user(user)
//                .buyProductList(buyProductList)
//                .sumPrice(sumPrice)
//                .deliveryFee(deliverFee)
//                .build();
        basket.setCount(allCount);
        basket.setSumPrice(sumPrice);
        basket.setDeliveryFee(deliverFee);
        basket.setBuyProductList(buyProductList);

//        Basket findId = basketRepository.findByUser_Id(user.getId());
//
//        if (findId == null) {
//            basketRepository.save(basket);
//        }else {
//            basket.setBuyProductList(basket.getBuyProductList());
//            basket.setDeliveryFee(basket.getDeliveryFee());
//            basket.setSumPrice(basket.getSumPrice());
//        }
        return new BasketResponseDto(basket);
    }
}
