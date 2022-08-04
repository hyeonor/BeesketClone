package com.beesket.beesketclone.controller;

import com.beesket.beesketclone.dto.SignupRequestDto;
import com.beesket.beesketclone.exception.CustomException;
import com.beesket.beesketclone.exception.ErrorCode;
import com.beesket.beesketclone.model.User;
import com.beesket.beesketclone.security.UserDetailsImpl;
import com.beesket.beesketclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;

    //회원가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
        String res = userService.registerUser(requestDto);
        if (res.equals("")) {
            return "회원가입 성공";
        } else {
            return res;
        }

    }

    //로그인 유저 정보
    @GetMapping("user/login/auth")
    public User userDetails(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.userInfo(userDetails);
    }
//      비회원일 때 장바구니 담기, 조회 등을 하는 코드
//      (장바구니 Controller에 적기, if문 안에는 비회원일 때이며 에러코드 400으로 띄우고 메세지 띄워줌, return 뒤에는 회원일때 원래 짰던 코드를 넣기, 실제로 적용한 코드는 BasketClone 최종 파일에 있음)
//     if (userDetails == null) {
//        // 유저가 없다는 의미이므로 비정상 페이지 리턴
//        throw new CustomException(ErrorCode.LOGIN_CHECK_CODE);
//    } else {
//        // 토큰 값이 있으므로 정상 페이지 리턴
//        // 1. 먼저 현재 접속 중인 유저의 토큰 값을 가져와야함
//        return "";
//    }

}
