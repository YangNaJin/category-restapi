package com.musinsa.cate.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDto {
    private long no;
    private String cateCode;
    private String cateSubCode;
    private String cateName;
    private int cateStatus;
}
