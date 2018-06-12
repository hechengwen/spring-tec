package hcw.tecservice.learn.configuration;

import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/23 14:23
 * Description:
 * Others:
 */
@Component
public class TestBean {

    private String username;
    private String url;
    private String password;

    public void sayHello(){
        System.out.println("TestBean sayHello...");
    }

/*    public String toString() {
        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }*/

    public void start() {
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean 销毁。。。");
    }

}
