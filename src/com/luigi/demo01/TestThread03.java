package com.luigi.demo01;


//创建线程方式二，实现runnable接口，重写run方法，执行线程需要丢入runnable接口实现类，调用start方法
public class TestThread03 implements Runnable {

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
        TestThread03 testThread03 = new TestThread03();

        Thread thread = new Thread(testThread03);
        //调用start()方法开启线程
        thread.start();

        //或者写出这样的形式，比较简练
        /*
        new Thread(testThread03).start();
         */


        //调用run()方法，与start()有什么不同
        //testThread01.run();

        for (int i = 0; i < 2000; i++) {
            System.out.println("--正在编程--" + i);
        }
    }
}
