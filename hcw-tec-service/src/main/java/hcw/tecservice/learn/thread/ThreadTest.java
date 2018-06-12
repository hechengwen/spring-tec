package hcw.tecservice.learn.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/24 13:42
 * Description:
 * Others:
 */
public class ThreadTest {


    /**
      * Author: hechengwen
      * Desc:
      * lock()：获取锁，如果锁被暂用则一直等待
      * unlock():释放锁
      * tryLock(): 注意返回类型是boolean，如果获取锁的时候锁被占用就返回false，否则返回true
      * tryLock(long time, TimeUnit unit)：比起tryLock()就是给了一个时间期限，保证等待参数时间
      * lockInterruptibly()：用该锁的获得方式，如果线程在获取锁的阶段进入了等待，那么可以中断此线程，先去做别的事
      *
      * Date: 2018/5/24 16:45
    */  
    private Lock lock = new ReentrantLock();

    public void method(Thread thread) {
        try {
            if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                System.out.println(thread.getName() + "想要获取锁");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        lock.lock();
        try {
            System.out.println("线程名" + thread.getName() + "获得了锁");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("线程名" + thread.getName() + "释放了锁");
        }

    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadTest.method(Thread.currentThread());
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadTest.method(Thread.currentThread());
            }
        }, "t2").start();
    }

}
