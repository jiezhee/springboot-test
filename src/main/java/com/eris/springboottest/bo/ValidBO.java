package com.eris.springboottest.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ValidBO {
    @NotNull(message = "can't be null")
    private String sss;
}
