package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.BasketRequestDto;
import com.beesket.beesketclone.dto.BasketResponseDto;
import com.beesket.beesketclone.exception.CustomException;
import com.beesket.beesketclone.model.*;
import com.beesket.beesketclone.repository.*;
import com.beesket.beesketclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;

@Component //class를 bean으로 만듦
@Service
@RequiredArgsConstructor
public class BasketService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final BuyProductListRepository buyProductListRepository;
    private final ImageRepository imageRepository;

    //장바구니 담기
    @Transactional
    public void saveBasket(BasketRequestDto basketRequestDto, UserDetailsImpl userDetails) {

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

        Product product = productRepository.findById(basketRequestDto.getProductId()).orElseThrow(
                () -> new IllegalArgumentException("상품이 존재하지 않습니다.")
        );

        BuyProductList find = buyProductListRepository.findByProduct_IdAndBasket(basketRequestDto.getProductId(),basket);

        List<Image> image = imageRepository.findAllByProductId(product.getId());

        if (find != null){
            find.setCount(basketRequestDto.getCount()+find.getCount());
        } else {
            BuyProductList buyProductList = BuyProductList.builder()
                    .basket(basket)
                    .product(product)
                    .imgUrl(image.get(0).getImgUrl())
                    .count(basketRequestDto.getCount())
                    .build();

            buyProductListRepository.save(buyProductList);
        }
    }

    //장바구니 조회 및 저장
    @Transactional
    public BasketResponseDto basketList(UserDetailsImpl userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원이 존재하지 않습니다.")
        );

        Basket basket = basketRepository.findByUser_Id(userDetails.getUser().getId());

        // 회원이 아닐 때
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

        basket.setCount(allCount);
        basket.setSumPrice(sumPrice);
        basket.setDeliveryFee(deliverFee);
        basket.setBuyProductList(buyProductList);

        return new BasketResponseDto(basket);
    }

    //장바구니 전체 삭제
    @Transactional
    public void deleteBasket(UserDetailsImpl userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("회원이 존재하지 않습니다.")
        );

        Basket basket = basketRepository.findByUser_Id(user.getId());
        List<BuyProductList> items = basket.getBuyProductList();

        basket.setBuyProductList(null);

        for (BuyProductList buyProductList : items){
            buyProductListRepository.deleteById(buyProductList.getId());
        }

        basketRepository.delete(basket);
    }
}
