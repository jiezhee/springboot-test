package com.eris.springboottest.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AspectTestService {

    @Autowired
    private Target target;

    @PostConstruct
    public void init(){
        target.test();
    }
}
