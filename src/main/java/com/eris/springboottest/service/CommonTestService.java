package com.eris.springboottest.service;

import com.eris.springboottest.bo.CommonBo;
import com.eris.springboottest.service.interandimpl.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CommonTestService {

    private final CommonService commonService;

    private final ServiceInterface serviceImpl1;

    @PostConstruct
    public void init() {
//        commonService.handle();
        String name = serviceImpl1.getClass().getName();
        System.out.println("name = " + name);
    }
}
