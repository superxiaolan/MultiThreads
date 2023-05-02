package com.luigi.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//测试线程池
public class TestThreadPool {
    public static void main(String[] args) {
        //1.创建服务，创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //2.执行Runnable接口的实现类
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());
        executorService.execute(new MyThread());

        //3.关闭连接
        executorService.shutdown();
    }

}

class MyThread implements Runnable{
    @Override
    public void run() {
        /*for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+i);
        }*/
        System.out.println(Thread.currentThread().getName());
    }
}