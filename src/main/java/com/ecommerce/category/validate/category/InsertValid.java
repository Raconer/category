package com.ecommerce.category.validate.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecommerce.category.core.code.ValidCode;
import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.repository.CategoryRepository;

@Component
public class InsertValid implements Validator {

    CategoryDto categoryDto;
    CategoryRepository categoryRepository;

    public InsertValid(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryDto.class.isAssignableFrom(clazz);
    }

    // Update시 Validate
    @Override
    public void validate(Object target, Errors errors) {

        categoryDto = (CategoryDto) target;
        Long parent = categoryDto.getParent();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", ValidCode.REQUIRED.getCode());
        // Insert시 parent 존재 여부 확인
        if (parent != null && parent > 0) {
            Long parentId = this.categoryRepository.findIdById(categoryDto.getParent());
            if (parentId == null) {
                errors.rejectValue("parent", ValidCode.CHECK.getMsg());
            }
        }
    }
}
