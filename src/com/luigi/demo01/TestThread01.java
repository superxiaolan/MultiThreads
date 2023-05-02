package com.luigi.demo01;


//创建线程的方式之一，继承Thread类，重写run()方法，调用start开启线程

//总结：注意，线程开启不一定执行，由CPU调度执行
public class TestThread01 extends Thread{

    //重写run()方法

    @Override
    public void run() {

        //run线程方法体
        for (int i = 0; i < 200; i++) {
            System.out.println("--正在看书--" + i);
        }
    }

    public static void main(String[] args) {
        //main线程，主线程

        //创建一个线程对象
        TestThread01 testThread01 = new TestThread01();

        //调用start()方法开启线程
        testThread01.start();

        //调用run()方法，与start()有什么不同
        //testThread01.run();

        for (int i = 0; i < 2000; i++) {
            System.out.println("--正在编程--" + i);
        }
    }
}
