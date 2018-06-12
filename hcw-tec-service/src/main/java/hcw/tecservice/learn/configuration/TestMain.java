package hcw.tecservice.learn.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/23 14:27
 * Description:
 * Others:
 */
public class TestMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        TestBean tb = (TestBean) context.getBean("testBean");
        tb.sayHello();
        System.out.println(tb);

        TestBean tb2 = (TestBean) context.getBean("testBean");
        tb2.sayHello();
        System.out.println(tb2);
    }
}
