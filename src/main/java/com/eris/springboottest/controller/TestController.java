package com.eris.springboottest.controller;

import com.eris.springboottest.bo.ValidBO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/valid")
    public String getProductInfo(@Valid @RequestBody ValidBO validBO) {
        return "hello";
    }

}
