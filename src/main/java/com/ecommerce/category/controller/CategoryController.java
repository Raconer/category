package com.ecommerce.category.controller;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.category.model.common.err.FieldErrs;
import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.service.CategoryService;
import com.ecommerce.category.validate.category.SaveValid;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    CategoryService categoryService;

    SaveValid saveValid;

    // save
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody CategoryDto categoryDto, BindingResult result) {
        saveValid.validate(categoryDto, result);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new FieldErrs(result.getFieldErrors()));
        }

        this.categoryService.save(categoryDto);

        return ResponseEntity.ok(categoryDto);

    }

}
