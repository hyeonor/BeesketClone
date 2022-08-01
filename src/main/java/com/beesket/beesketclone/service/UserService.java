package com.beesket.beesketclone.service;

import com.beesket.beesketclone.dto.LoginRequestDto;
import com.beesket.beesketclone.dto.SignupRequestDto;
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


        //회원 email 중복 확인
        Optional<User> found = userRepository.findByEmail(email);
        if(found.isPresent()){
            throw new IllegalArgumentException("중복된 email 입니다.");
        }

        //회원가입 조건
        String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        if(!Pattern.matches(pattern, email)){
            throw new IllegalArgumentException("이메일을 정확히 입력하세요.");
        } else if (!password.equals(password2)){
            return "비밀번호가 일치하지 않습니다.";
        } else if (password.length() < 4) {
            return "비밀번호를 4자 이상 입력하세요.";
        }


        //패스워드 인코딩
        password = passwordEncoder.encode(password);
        requestDto.setPassword(password);

        //유저 정보 저장
        User user = new User(email, password, name);
        userRepository.save(user);
        return error;

    }

    //로그인
    public Boolean login(LoginRequestDto loginRequestDto){
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElse(null);

        if(user != null){
            if(passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
                return false;
            }
        } else{
            return false;
        }
        return true;
    }

    public User userInfo(UserDetailsImpl userDetails) {
        Long id = userDetails.getUser().getId();
        String email = userDetails.getUser().getEmail();
        String name = userDetails.getUser().getName();
        User userInfo = new User(id, email, name);
        return userInfo;
    }

}
