package hcw.tecservice.learn.hashmap;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/4 9:23
 * Description:
 * Others:
 */
public class HashMapTest {

    private static String _name = "xiaohe";
    public int _age = 25;

    public static void main(String[] args) throws Exception {
        new HashMapTest().display();
    }

    private static void outerClass(){
        System.out.println("我是外部类静态方法");
    }

    /**
     * 静态内部类
     */
    static class InnerClass1{
        public static String name = "xiaosan";
        public void dispaly(){
            // 静态内部类可以直接访问外部类静态变量 ，即使是private的，不能访问非静态的成员变量和方法
            System.out.println("inner class1 : " + _name);
            outerClass();
        }
    }

    /**
     * 非静态内部类
     */
    class InnerClass2{
        // 非静态内部类，不能定义静态成员
        public String name = "xiaosi";
        public void display(){
            // 非静态内部类可以直接访问外部类的任何成员
            System.out.println("inner class2 : " + _name + " , age :" + _age);
        }
    }

    public void display(){
        System.out.println("外部类中可以直接访问静态内部类变量：" + InnerClass1.name);

        // 外部类可以直接通过new 内部类 来创建对象，不需要依赖外部类实例
        new InnerClass1().dispaly();

        // 创建非静态内部类方式：外部类.内部类 a = new 外部类().new 内部类();
        HashMapTest.InnerClass2 innerClass2 = new HashMapTest().new InnerClass2();
        innerClass2.display();
    }

}
