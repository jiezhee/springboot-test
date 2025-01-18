package com.eris.springboottest.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBeanImpl implements FactoryBean<FactoryObject> {

    @Override
    public FactoryObject getObject() throws Exception {
        return new FactoryObject(100);
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryObject.class;
    }
}
