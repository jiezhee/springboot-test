package com.eris.springboottest.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
public class CommonBo {
    private int a;

    @Autowired
    private CommonBo2 commonBo2;

}
