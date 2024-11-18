package com.eris.springboottest.gc;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class NewObjectComponent {


    List<Thread> threads = Lists.newArrayList();

//    @PostConstruct
    public void init() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            Thread myThread = new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " running..." + " threads count:" + threads.size());
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            myThread.start();
            threads.add(myThread);
            Thread.sleep(100);
        }
    }
}
