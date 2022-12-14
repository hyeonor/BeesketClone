package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.SignupRequestDto;
import com.beesket.beesketclone.exception.CustomException;
import com.beesket.beesketclone.exception.ErrorCode;
import com.beesket.beesketclone.model.User;
import com.beesket.beesketclone.repository.UserRepository;
import com.beesket.beesketclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String registerUser(SignupRequestDto requestDto){
        String error = "";
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        String password2 = requestDto.getPassword2();
        String name = requestDto.getName();
        String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

        //회원 email 중복 확인
        Optional<User> found = userRepository.findByEmail(email);
        if(found.isPresent()){
            throw new CustomException(ErrorCode.EMAIL_DUPLICATION_CODE);
        }

        //회원가입 조건
        if(!Pattern.matches(pattern, email)){
            throw new CustomException(ErrorCode.EMAIL_FORM_CODE);
        } else if (!password.equals(password2)){
            throw new CustomException(ErrorCode.PASSWORD_CHECK_CODE);
        } else if (password.length() < 4) {
            throw new CustomException(ErrorCode.PASSWORD_LENGTH_CODE);
        }


        //패스워드 인코딩
        password = passwordEncoder.encode(password);
        requestDto.setPassword(password);

        //유저 정보 저장
        User user = new User(email, password, name);
        userRepository.save(user);
        return error;

    }

    public User userInfo(UserDetailsImpl userDetails) {
        Long id = userDetails.getUser().getId();
        String email = userDetails.getUser().getEmail();
        String name = userDetails.getUser().getName();
        User userInfo = new User(id, email, name);
        return userInfo;
    }

}
