package com.eris.springboottest.aspect;

import org.springframework.stereotype.Service;

@Service
public class Target {

    public void test(){
        System.out.println("========目标方法");
    }
}
