package com.ecommerce.category.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import com.ecommerce.category.model.common.err.FieldErr;

public class ErrorUtils {

    private ErrorUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<FieldErr> getFieldErr(List<FieldError> errors) {

        List<FieldErr> validates = new ArrayList<>();

        errors.stream().forEach(err -> validates.add(new FieldErr(err.getField(), err.getCode())));

        return validates;
    }
}
