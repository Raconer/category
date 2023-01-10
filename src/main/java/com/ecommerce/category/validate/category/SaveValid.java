package com.ecommerce.category.validate.category;

import org.springframework.stereotype.Component;
import org.h2.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecommerce.category.core.code.ValidCode;
import com.ecommerce.category.model.dto.category.CategoryDto;

@Component
public class SaveValid implements Validator {

    CategoryDto categoryDto;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryDto.class.isAssignableFrom(clazz);
    }

    // Update시 Validate
    @Override
    public void validate(Object target, Errors errors) {

        categoryDto = (CategoryDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", ValidCode.REQUIRED.getCode());

        if (categoryDto.getId().equals(categoryDto.getParent())) {
            errors.rejectValue("parent", ValidCode.CHECK.getMsg());
        }

        if (StringUtils.isNullOrEmpty(categoryDto.getName())
                && categoryDto.getSort() == null
                && categoryDto.getParent() == null) { // Update
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", ValidCode.REQUIRED.getCode());
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sort", ValidCode.REQUIRED.getCode());
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parent", ValidCode.REQUIRED.getCode());
        }
    }
}
