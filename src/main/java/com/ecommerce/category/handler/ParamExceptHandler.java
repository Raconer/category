package com.ecommerce.category.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ecommerce.category.core.code.ValidCode;
import com.ecommerce.category.model.common.err.FieldErrs;

@RestControllerAdvice
public class ParamExceptHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        return ResponseEntity.badRequest().body(new FieldErrs(name, ValidCode.REQUIRED));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMismatchException(MethodArgumentTypeMismatchException ex) {

        return ResponseEntity.badRequest().body(new FieldErrs(ex.getName(), ValidCode.REQUIRED));
    }
}
