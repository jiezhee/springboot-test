package com.eris.springboottest.service;

import com.eris.springboottest.bo.ValidBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;


@Slf4j
@Service
public class ValidateNotNullService {


    @PostConstruct
    public void init() throws IOException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        ValidBO model = new ValidBO();
        Set<ConstraintViolation<ValidBO>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<ValidBO> violation : violations) {
                System.out.println("======" + violation.getPropertyPath() + ": " + violation.getMessage());
            }
        }

    }
}
