package com.eris.springboottest.listener;

import org.springframework.context.ApplicationEvent;

// 事件A
public class CustomEventA extends ApplicationEvent {
    private String message;
    public CustomEventA(Object source, String message) {
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

