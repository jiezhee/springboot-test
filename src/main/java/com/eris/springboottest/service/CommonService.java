package com.eris.springboottest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
//@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "test")
@Profile("test")
public class CommonService {

    @PostConstruct
    public void init() {
    }

    public void handle(){
        log.info("commmonService handle...");
    }
}
