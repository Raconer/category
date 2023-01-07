package com.ecommerce.category.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import com.ecommerce.category.model.dto.category.CategoryDto;

public class CategorySpec {

    public static Specification<CategoryDto> parent(Long parent) {
        return (root, query, cb) -> cb.equal(root.get("parent"), parent);
    }

    public static Specification<CategoryDto> sortBetween(int from, int to) {
        return (root, query, cb) -> cb.between(root.get("sort"), from, to);
    }

}
