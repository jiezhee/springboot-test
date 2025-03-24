package com.eris.springboottest;

import com.eris.springboottest.config.MyConfiguration;
import com.eris.springboottest.factorybean.MyFactoryBeanImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import other.pack.ImportTest;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@ServletComponentScan
@Import(ImportTest.class)
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

//        testEnv(context);

//        testBeanFactory(context);

//        testFactoryBean(context);
    }


    public static void testEnv(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        String server = environment.getProperty("eris.database");
        System.out.println(server);
    }

    public static void testBeanFactory(ConfigurableApplicationContext context) {
        MyConfiguration myConfiguration = (MyConfiguration) context.getBean("myConfiguration");
        Object myObj1 = context.getBean("myObj1");
        Object obj1 = myConfiguration.myObj1();
        System.out.println(obj1 == myObj1);
    }

    public static void testFactoryBean(ConfigurableApplicationContext context) {
        MyFactoryBeanImpl myFactoryBean = (MyFactoryBeanImpl) context.getBean("myFactoryBeanImpl");
        System.out.println(myFactoryBean);
    }
}
