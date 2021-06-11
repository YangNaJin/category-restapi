package com.musinsa.cate.controller;

import com.musinsa.cate.dto.CategoryDto;
import com.musinsa.cate.entity.Category;
import com.musinsa.cate.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 등록 요청
     * @param cate
     * @return
     */
    @PostMapping("/v1/saveCate")
    public ResponseEntity<Category> saveCategoryInfo(
            @RequestBody CategoryDto cate) {

        return new ResponseEntity<>(categoryService.saveCategory(cate),
                HttpStatus.OK);
    }

    /**
     * 카테고리 삭제요청
     * @param cate
     * @return
     */
    @PostMapping("/v1/removeCate")
    public ResponseEntity<Category> removeCategoryInfo(
            @RequestBody CategoryDto cate) {

        log.info("No : {}", cate.getNo());
        return new ResponseEntity<>(categoryService.removeCategoryInfo(cate),
                HttpStatus.OK);
    }


    /**
     * 카테고리 수정 요청
     * @param cate
     * @return
     */
    @PostMapping("/v1/updateCate")
    public ResponseEntity<Category> updateCategoryInfo(
            @RequestBody CategoryDto cate) {

        return new ResponseEntity<>(categoryService.updateCategory(cate),
                HttpStatus.OK);
    }


    /**
     *
     * @param cateCode
     * @return
     */
    @GetMapping("/v1/search")
    public ResponseEntity<List<Category>>getCategoryInfoList(
            @RequestParam(value = "cateCode", required = false, defaultValue = "") String cateCode){

        log.info("cateCode : {}", cateCode);
        return new ResponseEntity<>(categoryService.searchCategoryInfoList(cateCode),
                HttpStatus.OK);
    }

}
