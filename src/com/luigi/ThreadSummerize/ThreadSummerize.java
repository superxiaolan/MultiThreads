package com.luigi.ThreadSummerize;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//回顾总结线程的创建,三种方式
//其实还有一种，用线程池的方式，所以一共有四种方式创建线程
public class ThreadSummerize {
    public static void main(String[] args) {
        //1.extend Thread方式创建并启动线程
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();//等价于 new MyThread1().start();

        //2.implements Runnable接口的方式创建并启动线程
        new Thread(new MyThread2()).start();

        //3.implements Callable接口的方式创建并启动线程
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread3());
        new Thread(futureTask).start();

        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

//1.继承Thread类
class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread");
    }
}

//2.实现Runnable接口
class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("MyThread2");
    }
}

//3.实现Callable接口
class MyThread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("MyThread3");
        return 114514;

    }
}