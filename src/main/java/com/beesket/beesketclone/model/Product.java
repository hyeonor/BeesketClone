package com.beesket.beesketclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

    //제품 이름
    @Column(nullable = false)
    private String productName;

    //제품 가격
    @Column(nullable = false)
    private int price;

    //카테고리 이름
    @Column(nullable = false)
    private String categoryName;

    //제품 상세 설명
    @Column(nullable = false)
    private String productDetail;

    // Product에서 Comment에 대한 정보 넣기
    public void addComment(Comment comment) {
        this.comment.add(comment);
    }
}