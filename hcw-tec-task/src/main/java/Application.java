import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/17 9:48
 * Description:
 * Others:
 */
public class Application {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:applicationContext*.xml").start();
    }
}
