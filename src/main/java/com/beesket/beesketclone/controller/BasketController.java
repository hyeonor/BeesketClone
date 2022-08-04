package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.BasketRequestDto;
import com.beesket.beesketclone.dto.BasketResponseDto;
import com.beesket.beesketclone.exception.CustomException;
import com.beesket.beesketclone.exception.ErrorCode;
import com.beesket.beesketclone.model.Basket;
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
    public ResponseEntity<String> saveBasket(@RequestBody BasketRequestDto basketRequestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails == null) {
            // 유저가 없다는 의미이므로 비정상 페이지 리턴
            throw new CustomException(ErrorCode.LOGIN_CHECK_CODE);
        } else {
            // 토큰 값이 있으므로 정상 페이지 리턴
            // 1. 먼저 현재 접속 중인 유저의 토큰 값을 가져와야함
            basketService.saveBasket(basketRequestDto, userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body("장바구니가 담겼습니다.");
        }
    }

    //장바구니 조회 및 저장
    @GetMapping("/product/basketList")
    public ResponseEntity<BasketResponseDto> basketList(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails == null) {
            // 유저가 없다는 의미이므로 비정상 페이지 리턴
            throw new CustomException(ErrorCode.LOGIN_CHECK_CODE);
        } else {
            // 토큰 값이 있으므로 정상 페이지 리턴
            // 1. 먼저 현재 접속 중인 유저의 토큰 값을 가져와야함
            BasketResponseDto basketResponseDto = basketService.basketList(userDetails);
            return ResponseEntity.ok().body(basketResponseDto);
        }
    }

    //장바구니 전체 삭제
    @DeleteMapping("/product/basketList")
    public ResponseEntity<String> deleteBasket(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        basketService.deleteBasket(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("장바구니 삭제 되었습니다.");
    }
}
