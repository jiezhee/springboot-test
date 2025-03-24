package com.eris.springboottest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "test")
@Profile("test")
public class CommonService {

    public void handle(){
        log.info("commmonService handle...");
    }
}
