package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.BasketDto;
import com.beesket.beesketclone.dto.BasketListDto;
import com.beesket.beesketclone.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    //구매 목록 저장
    @PostMapping("/product/basketList")
    public void createBasketList(@RequestBody BasketListDto basketListDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        basketService.createBasketList(basketListDto, userDetails);
    }

}
