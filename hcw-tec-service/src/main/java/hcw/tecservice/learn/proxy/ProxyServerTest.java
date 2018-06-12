package hcw.tecservice.learn.proxy;


import java.lang.reflect.Method;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/2/5 14:56
 * Description:
 * Others:
 */
public class ProxyServerTest {

    public static void main(String[] args) throws Exception{
//        Server server = (Server) new ProxyServer().getInstance(new ServerImpl());//返回的这是一个代理类
//        server.printa("b");
//        server.printb("b123");

        /**
         * 反射
         */
        Class clazz = Class.forName("hcw.tecservice.learn.proxy.ServerImpl");
        Server server = (Server) clazz.newInstance();
        ClassLoader classLoader = clazz.getClassLoader();
        Method method = clazz.getMethod("printa",String.class);
        method.invoke(server,"1");
    }
}
