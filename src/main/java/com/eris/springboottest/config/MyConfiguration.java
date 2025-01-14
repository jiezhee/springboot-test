package com.eris.springboottest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MyConfiguration {

    @Bean
    public Object myObj1(){
        return new Object();
    }

}
