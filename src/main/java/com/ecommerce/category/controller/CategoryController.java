package com.ecommerce.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.category.service.CategoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> name() {
        this.categoryService.select();
        log.info("Start Main Rest Controller");
        
        return ResponseEntity.ok(HttpStatus.OK);
    }
    
}
