import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/15 14:06
 * Description:
 * Others:
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        System.out.println(context.getDisplayName() + ": here");
        context.start();
        getLocalip();
        System.out.println("服务已经启动...");
        System.in.read();
    }

    private static void getLocalip() {
        try {
            System.out.println("服务暴露的ip: " + java.net.InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
