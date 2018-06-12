package hcw.tecservice.learn.object;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/3 15:06
 * Description:
 * Others:
 */
public class InvokeClass {

    private String time = "15:11";
    public String follower;
    protected int close;

    public void a1(){
        System.out.println("a1");
    }

    private void a2(){
        System.out.println("a2");
    }

    protected void a3(){
        System.out.println("a3");
    }

    public static void main(String[] args) throws Exception{
        Field[] fields = Object.class.getDeclaredFields();//获得本类所有的字段
        for (Field field : fields){
            field.setAccessible(true);//设置可以访问私有属性
            System.out.println("class.getDeclaredFields()-> "+ field.getModifiers()+ "---" + field.getName() + " : " + field.get(new Object()));
        }
        System.out.println();
        Field[] fields1 = Object.class.getFields();// 获得本类的public修饰的字段和父类public修饰的字段
        for (Field field : fields1){
            System.out.println("class.getFields()-> "+ field.getModifiers()+ "---" + field.getName() + " : " + field.get(new Object()));
        }
        System.out.println();
        Field[] fields2 = Object.class.getSuperclass().getDeclaredFields();// 获得父类的所有字段，包括private
        for (Field field : fields2){
//            field.setAccessible(true);//设置可以访问私有属性,注：在获取本类可以不用设置
            System.out.println("class.getSuperclass().getDeclaredFields()-> "+ field.getModifiers()+ "---" + field.getName() + " : " + field.get(new Object()));
        }
        System.out.println();

        Method[] methods = Object.class.getMethods();// 获得所有的public修饰的方法
        for (Method method : methods) {
            System.out.println("class.getMethods-> " + method.getModifiers() + "---"+ method.getName());
        }
        System.out.println();
        Method[] methods1 = Object.class.getDeclaredMethods();// 获得本类所有的方法，包括private
        for (Method method : methods1) {
            if (method.getModifiers() == 2) { //修饰符（private：2，public：1，protected：4，default：0）
                method.setAccessible(true);//放射获取到private修饰的方法
            }
            System.out.println("class.getDeclaredMethods-> " + method.getModifiers() + "---"+ method.getName() + "()");
            method.invoke(new Object(),"调用成功");
        }

//        Object.class.getMethod("a2",String.class);
    }
}
