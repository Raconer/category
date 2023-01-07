package com.ecommerce.category.repository.spec;

import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;

import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.model.dto.category.CategoryDto_;

public class CategorySpec {

    public static Specification<CategoryDto> findUpdateSort(Long parent, int from, int to) {
        // Parent와 변경 범위 데이터 가져오는 쿼리
        return CategorySpec.parent(parent)
                .and(CategorySpec.sortBetween(from, to));
    }

    public static Specification<CategoryDto> parent(Long parent) {
        return (root, query, cb) -> cb.equal(root.get("parent"), parent);
    }

    public static Specification<CategoryDto> sortBetween(int from, int to) {
        return (root, query, cb) -> cb.between(root.get("sort"), from, to);
    }

}
