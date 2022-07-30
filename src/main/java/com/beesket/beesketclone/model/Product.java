package com.beesket.beesketclone.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String imgUrl; //이미지 URL

    @Column(nullable = false)
    private String productName; //제품 이름

    @Column(nullable = false)
    private int price; //제품 가격

    @Column(nullable = false)
    private String categoryName; //카테고리 이름
}