package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.BasketProductDto;
import com.beesket.beesketclone.dto.BasketResponseDto;
import com.beesket.beesketclone.security.UserDetailsImpl;
import com.beesket.beesketclone.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    //장바구니 담기
    @PostMapping("/product/basketList")
    public void saveBasket(@RequestBody BasketProductDto basketProductDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        basketService.saveBasket(basketProductDto, userDetails);
    }

//    장바구니 조회
    @GetMapping("/product/basketList")
    public BasketResponseDto basketList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return basketService.basketList(userDetails);
    }

}
