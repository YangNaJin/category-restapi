package com.musinsa.cate.repository;

import com.musinsa.cate.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class CategoryRepository{

    @PersistenceContext
    private EntityManager em;

    public Category save(Category category) {
        em.persist(category);
        return category;
    }

    public Category findOne(Long no) {

        return em.find(Category.class, no);
    }

    public List<Category> findByCateCode(String cateCode) {
        return em.createQuery("SELECT c FROM Category c where c.cateStatus = 1 AND c.cateCode = :cateCode AND c.cateSubCode <> :cateSubCode", Category.class)
                .setParameter("cateCode", cateCode)
                .setParameter("cateSubCode", cateCode+"000")
                .getResultList();
    }

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c where c.cateStatus = 1", Category.class)
                .getResultList();
    }
}
