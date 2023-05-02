package com.luigi.TreadState;

public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();

        //看CPU心情对礼让的线程进行调度
        new Thread(myYield,"线程1").start();
        new Thread(myYield,"线程2").start();
        new Thread(myYield,"线程3").start();
        new Thread(myYield,"线程4").start();
        new Thread(myYield,"线程5").start();
        new Thread(myYield,"线程6").start();
        new Thread(myYield,"线程7").start();
    }
}

class MyYield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ "线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+ "线程停止执行");
    }
}
