package com.eris.springboottest.controller;

import com.eris.springboottest.bo.ValidBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/aa")
public class BaseController {

    @RequestMapping("/valid")
    public void method1(@Valid @RequestParam ValidBO validBO){
    }
}
