package com.eris.springboottest.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public final class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @PostConstruct
    public void init(){
        Object commonBo2 = getBean("commonBo2");
    }

    /**
     * 通过spring配置文件中配置的bean id取得bean对象
     * @param id spring bean ID值
     * @return spring bean对象
     */
    public static Object getBean(String id) {
        if (ctx == null) {
            throw new NullPointerException("ApplicationContext is null");
        }
        return ctx.getBean(id);
    }

    /**
     * 根据类型从Spring容器中获取bean实例
     * @param clazz spring bean type
     * @return spring bean对象
     */
    public static <T> T getBeanByType(Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationcontext)
            throws BeansException {
        ctx = applicationcontext;
    }
}
