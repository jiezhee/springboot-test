package com.eris.springboottest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ContextService extends ApplicationObjectSupport {

    public ApplicationContext get(){
        return super.obtainApplicationContext();
    }
}
