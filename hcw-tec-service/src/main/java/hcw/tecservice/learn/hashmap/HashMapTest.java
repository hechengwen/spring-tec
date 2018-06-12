package hcw.tecservice.learn.hashmap;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    public void hashTable() {
        Map hashmap = new HashMap();

        Map<String, String> hashtable = new Hashtable();

        Map<String, String> synchronizedHashMap = Collections.synchronizedMap(new HashMap<>());

        Map<String, String> concurrentHashMap = new ConcurrentHashMap();

        concurrentHashMap.put("", "");

        Set set = new HashSet();

        List arrayList = new ArrayList();

        Vector vector = new Vector();

        List linkedList = new LinkedList<>();
    }

    public static void main(String[] args) throws Exception {
        Thread thread2 = new Thread(new Thread2());
        thread2.start();
        Thread thread = new Thread(new Thread1());
        // 守护线程,在执行start方法之前设置值
//        thread.setDaemon(true);
        thread.start();
        thread.join();

//        Thread.sleep(6000);
        System.out.println("mian线程退出。。。");
        /*Thread2 th = new Thread2();
        Thread thread1 = new Thread(th, "1");
        Thread thread2 = new Thread(th, "2");
        Thread thread3 = new Thread(th, "3");
        Thread thread4 = new Thread(th, "4");
        Thread thread5 = new Thread(th, "5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();*/

    }

    static class Thread2 implements Runnable {

        @Override
        public void run() {
            for (int i =0;i<5;i++){
                System.out.println("由 " + Thread.currentThread().getName() + " 计算，count=" + i);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }
        }
    }

    /*static class Thread2 implements Runnable {

        AtomicInteger count = new AtomicInteger(5);

        @Override
        public void run() {
            System.out.println("由 " + Thread.currentThread().getName() + " 计算，count=" + count.getAndDecrement());
        }
    }*/

    /**
     * lock来实现count--的原子性操作
     */
    /*static class Thread2 implements Runnable {

        private int count = 5;

        Lock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                lock.lock();
                count--;
                System.out.println("由 " + Thread.currentThread().getName() + " 计算，count=" + count);
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }*/

    static class Thread1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("我执行了：" + i + "次");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }
            System.out.println("thread1 执行完成。。。");
        }
    }

    private static void outerClass() {
        System.out.println("我是外部类静态方法");
    }

    /**
     * 静态内部类
     */
    static class InnerClass1 {
        public static String name = "xiaosan";

        public void dispaly() {
            // 静态内部类可以直接访问外部类静态变量 ，即使是private的，不能访问非静态的成员变量和方法
            System.out.println("inner class1 : " + _name);
            outerClass();
        }
    }

    /**
     * 非静态内部类
     */
    class InnerClass2 {
        // 非静态内部类，不能定义静态成员
        public String name = "xiaosi";

        public void display() {
            // 非静态内部类可以直接访问外部类的任何成员
            System.out.println("inner class2 : " + _name + " , age :" + _age);
        }
    }

    public void display() {
        System.out.println("外部类中可以直接访问静态内部类变量：" + InnerClass1.name);

        // 外部类可以直接通过new 内部类 来创建对象，不需要依赖外部类实例
        new InnerClass1().dispaly();

        // 创建非静态内部类方式：外部类.内部类 a = new 外部类().new 内部类();
        HashMapTest.InnerClass2 innerClass2 = new HashMapTest().new InnerClass2();
        innerClass2.display();
    }

}
