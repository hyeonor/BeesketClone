package com.beesket.beesketclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing //생성일자 수정일자 반영
@SpringBootApplication
public class BeesketCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeesketCloneApplication.class, args);
    }

    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));

    }

}
