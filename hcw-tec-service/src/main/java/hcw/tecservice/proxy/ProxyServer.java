package hcw.tecservice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/2/5 14:46
 * Description:
 * Others:动态代理Demo
 */
public class ProxyServer implements InvocationHandler{

    private Object object;

    // 被代理类的对象
    public Object getInstance(Object object){
        this.object = object;
        // 绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("1");
        // 第一个参数是object,也就是被代理类的对象；第二个参数是方法中的参数
        Object o = method.invoke(object,args);
        System.out.println("2");
        return o;
    }

}
