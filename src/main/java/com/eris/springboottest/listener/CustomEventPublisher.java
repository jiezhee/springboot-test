package com.eris.springboottest.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
// 这里将这个CustomEvent事件发布器也设置成一个事件监听器了，去监听ContextRefreshedEvent事件，当这个时间发生之后就会自动去发布CustomEvent事件
public class CustomEventPublisher implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // 利用容器刷新好的消息作为触发，去发布两条自定义的事件
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 创建事件
        CustomEventA eventA = new CustomEventA(applicationContext , "我是AAAA");
        CustomEventB eventB = new CustomEventB(applicationContext , "我是BBBB");
        // 发布事件
        applicationContext.publishEvent(eventA);
        applicationContext.publishEvent(eventB);
    }
}