package com.musinsa.cate.service;

import com.musinsa.cate.dto.CategoryDto;
import com.musinsa.cate.entity.Category;
import com.musinsa.cate.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 등록 요청
     * @param categoryDto
     * @return
     */
    @Transactional
    public Category saveCategory(CategoryDto categoryDto) {

        String cateSubCode = Optional.ofNullable(categoryDto.getCateSubCode()).orElse("000").equals("000") ? categoryDto.getCateCode() + "000" : categoryDto.getCateSubCode();
        Category cate = Category.builder()
                .cateCode(categoryDto.getCateCode())
                .cateName(categoryDto.getCateName())
                .cateSubCode(cateSubCode)
                .cateStatus(1)
                .build();

        Category category = categoryRepository.save(cate);
        log.info("카테고리 등록 결과 :{}", category);

        return category;
    }

    /**
     * 카테고리 업데이트
     * @param categoryDto
     * @return
     */
    @Transactional
    public Category updateCategory(CategoryDto categoryDto) {

        Category cate = Category.builder()
                .no(categoryDto.getNo())
                .build();

        Category categoryResult = categoryRepository.findOne(cate.getNo());
        categoryResult.setCateCode(categoryDto.getCateCode());
        categoryResult.setCateSubCode(categoryDto.getCateSubCode());
        categoryResult.setCateName(categoryDto.getCateName());
        return categoryResult;
    }

    /**
     * 카테고리 삭제
     * 상태값 0으로 변경
     * @param categoryDto
     * @return
     */
    @Transactional
    public Category removeCategoryInfo(CategoryDto categoryDto) {

        Category cate = Category.builder()
                .no(categoryDto.getNo())
                .build();

        Category categoryResult = categoryRepository.findOne(cate.getNo());
        categoryResult.setCateStatus(0);
        log.info("카테고리 삭제 결과 :{}", categoryResult);
        return categoryResult;
    }

    public List<Category> searchCategoryInfoList(String cateCode) {

        List<Category> result;
        if ("".equals(cateCode)){
            result = categoryRepository.findAll();
        } else {
            result = categoryRepository.findByCateCode(cateCode);
        }
        log.info("카테고리 리스트 검색 결과 :{}", result);
        return result;
    }
}
