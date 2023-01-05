package com.ecommerce.category.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.category.core.code.ValidCode;
import com.ecommerce.category.model.common.err.FieldErr;
import com.ecommerce.category.model.common.err.FieldErrs;
import com.ecommerce.category.model.dto.category.CategoryDto;
import com.ecommerce.category.model.vo.category.CategoryVo;
import com.ecommerce.category.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    CategoryService categoryService;

    // Create
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid CategoryVo categoryVo, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new FieldErrs(result.getFieldErrors()));
        }

        CategoryDto categoryDto = this.categoryService.insert(categoryVo);
        if (categoryDto.getId() != null) {
            return ResponseEntity.ok(categoryDto);
        }

        return ResponseEntity.badRequest().body(new FieldErr("name", ValidCode.ALREADY));

    }

    // Read
    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(HttpStatus.OK);
    }
    // Update
    // Delete
}
