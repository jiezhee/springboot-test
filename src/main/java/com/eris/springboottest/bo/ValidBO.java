package com.eris.springboottest.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class ValidBO {

    @NotBlank(message = "can't be null")
    private String sss;

    @NotNull
    private Integer age;
}
