package hcw.tecservice.learn.object;

import hcw.tecservice.learn.anno.MethodInfo;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/3 15:05
 * Description:
 * Others:
 */
public class Object extends InvokeClass{
    private String name = "hechengwen";
    private String hobby;
    public String address;
    protected int age;
    String company;

    public void a1(String a){
        System.out.println("a11"+a);
    }

    private void a2(String a){
        System.out.println("a22" + a);
    }

    protected void a3(String a){
        System.out.println("a33"+ a);
    }

    void a4(String a) {
        System.out.println("a44"+ a);
    }

}
