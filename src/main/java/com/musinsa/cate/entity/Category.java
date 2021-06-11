package com.musinsa.cate.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false, length = 10)
    private String cateCode;

    @Column(length = 10)
    private String cateSubCode;

    @Column(nullable = false, length = 50)
    private String cateName;

    @Column(nullable = false, length = 1)
    private int cateStatus;
}
