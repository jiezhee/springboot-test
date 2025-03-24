package com.eris.springboottest;

import com.eris.springboottest.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class ApplicationTests {

    @Resource
    private CommonService commonService;

    @Test
    void test1() {
        log.info("test1");
        commonService.handle();
    }

    @Test
    void test2() {
        log.info("test2");
    }

}
