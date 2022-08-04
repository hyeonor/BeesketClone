package com.beesket.beesketclone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//다른 출처의 자원을 공유할 수 있도록 권한 설정
//CORS 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*") //CORS를 적용할 URL패턴을 정의
                .allowedOrigins(""); //자원 공유를 허락할 Origin을 지정
    }

}