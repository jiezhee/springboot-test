package com.eris.springboottest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
//
//    @Autowired
//    private ChildA childA;

    @PostMapping("/aa")
    public String getProductInfo() {
        System.out.println("aaa");
        return "hello";
    }

}
