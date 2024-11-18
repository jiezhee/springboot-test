package com.eris.springboottest.listener;

import org.springframework.context.ApplicationEvent;

// 事件B
public class CustomEventB extends ApplicationEvent {
    private String message;
    public CustomEventB(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

