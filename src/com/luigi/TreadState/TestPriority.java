package com.luigi.TreadState;

public class TestPriority {

    public static void main(String[] args) {

        //主线程默认优先级
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();

        Thread t1 = new Thread(myPriority);
        Thread t2 = new Thread(myPriority);
        Thread t3 = new Thread(myPriority);
        Thread t4 = new Thread(myPriority);
        Thread t5 = new Thread(myPriority);
        Thread t6 = new Thread(myPriority);

        //先设置优先级，再启动
        t1.start();

        t2.setPriority(Thread.MIN_PRIORITY);//最低优先级
        t2.start();

        t3.setPriority(Thread.MAX_PRIORITY);//最高优先级
        t3.start();

        t4.setPriority(4);
        t4.start();

        t5.setPriority(7);
        t5.start();

        t6.setPriority(8);
        t6.start();

    }
}

class MyPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());
    }
}
