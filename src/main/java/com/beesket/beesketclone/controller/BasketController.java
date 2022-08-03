package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.BasketProductDto;
import com.beesket.beesketclone.dto.BasketResponseDto;
import com.beesket.beesketclone.security.UserDetailsImpl;
import com.beesket.beesketclone.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    //장바구니 담기
    @PostMapping("/product/basketList")
    public ResponseEntity saveBasket(@RequestBody BasketProductDto basketProductDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        basketService.saveBasket(basketProductDto, userDetails);
        return new ResponseEntity("장바구니가 담겼습니다.", HttpStatus.OK);
    }

    //장바구니 조회
    @GetMapping("/product/basketList")
    public ResponseEntity basketList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        BasketResponseDto basketResponseDto = basketService.basketList(userDetails);
        return new ResponseEntity(basketResponseDto, HttpStatus.OK);
    }

}
