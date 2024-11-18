package com.eris.springboottest.anno;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceA {

    @Async
    @Transactional
    public void test(){
        System.out.println(111);
        throw new RuntimeException("111");
    }
}
