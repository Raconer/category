package com.ecommerce.category.model.common.err;

import java.util.List;

import org.springframework.validation.FieldError;

import com.ecommerce.category.core.utils.ErrorUtils;

import lombok.Data;

@Data
public class FieldErrs {
    private List<FieldErr> errs;

    public FieldErrs(List<FieldError> errors) {
        this.errs = ErrorUtils.getFieldErr(errors);
    }

}
