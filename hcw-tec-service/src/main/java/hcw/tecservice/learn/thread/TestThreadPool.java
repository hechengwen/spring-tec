package hcw.tecservice.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/6/8 13:46
 * Description:
 * Others:
 */
public class TestThreadPool {

    public static void main(String[] args) {
        /**
         * 创建一个可缓存的线程池，调用execute将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
         * 缓存型池子通常用于执行一些生存期很短的异步型任务,对于生存期短的异步任务，它是 Executor的首选。
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            fixedThreadPool.execute(new TestRunnable());
        }
    }

    static class TestRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程被调用了");
        }
    }

}
