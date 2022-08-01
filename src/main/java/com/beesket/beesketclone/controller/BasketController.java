package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.BasketRequestDto;
import com.beesket.beesketclone.dto.BasketResponseDto;
import com.beesket.beesketclone.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    //장바구니 담기
    @PostMapping("/product/basket")
    public void saveBasket(@RequestBody BasketRequestDto basketRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        basketService.saveBasket(basketRequestDto, userDetails);
    }

    //장바구니 조회
    @GetMapping("/product/basketList")
    public List<BasketResponseDto> showBasket(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return basketService.showBasket(userDetails);
    }

}
