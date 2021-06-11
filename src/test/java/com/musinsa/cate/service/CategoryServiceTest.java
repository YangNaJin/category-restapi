package com.musinsa.cate.service;

import com.musinsa.cate.dto.CategoryDto;
import com.musinsa.cate.entity.Category;
import com.musinsa.cate.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void 카테고리등록() {
        //given
        CategoryDto cate = CategoryDto.builder()
                .cateCode("002")
                .cateName("상의")
                .build();

        //when
        Category category = categoryService.saveCategory(cate);

        //then
        assertEquals(category, categoryRepository.findOne(category.getNo()));

    }

    @Test
    void 카테고리수정() {
        //given
        CategoryDto cate1 = CategoryDto.builder()
                .cateCode("001")
                .cateName("상의")
                .build();
        Category category1 = categoryService.saveCategory(cate1);

        //when
        CategoryDto cate2 = CategoryDto.builder()
                .no(category1.getNo())
                .cateCode("002")
                .cateName("상의")
                .build();
        Category updateCategory = categoryService.updateCategory(cate2);

        //then
        assertEquals(category1.getNo(), categoryRepository.findOne(updateCategory.getNo()).getNo());
    }

    @Test
    void 카테고리삭제() {
        //given
        CategoryDto cate1 = CategoryDto.builder()
                .cateCode("001")
                .cateName("상의")
                .build();
        Category category1 = categoryService.saveCategory(cate1);
        CategoryDto cate2 = CategoryDto.builder()
                .no(category1.getNo())
                .build();

        //when
        Category removeCategory = categoryService.removeCategoryInfo(cate2);

        //then
        assertEquals(removeCategory.getCateStatus(), 0);
    }

    @Test
    void 카테고리조회() {
        //given
        CategoryDto cate1 = CategoryDto.builder()
                .cateCode("001")
                .cateName("상의")
                .build();
        CategoryDto cate2 = CategoryDto.builder()
                .cateCode("001")
                .cateSubCode("001001")
                .cateName("반팔 티셔츠")
                .build();
        CategoryDto cate3 = CategoryDto.builder()
                .cateCode("001")
                .cateSubCode("001002")
                .cateName("긴팔 티셔츠")
                .build();
        CategoryDto cate4 = CategoryDto.builder()
                .cateCode("001")
                .cateSubCode("001003")
                .cateName("민소매 티셔츠")
                .build();

        categoryService.saveCategory(cate1);
        categoryService.saveCategory(cate2);
        categoryService.saveCategory(cate3);
        categoryService.saveCategory(cate4);

        //when
        List<Category> subCategoryList = categoryService.searchCategoryInfoList("001");
        List<Category> allList = categoryService.searchCategoryInfoList("");

        //then
        assertEquals(subCategoryList.size(), 3);
        assertEquals(allList.size(), 4);

    }
}