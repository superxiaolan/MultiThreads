package com.luigi.demo01;

//多个线程同时操作同一个对象
//买火车票的例子

//发现问题：多个线程操作同一个资源的情况下，线程不安全，数据紊乱，
public class TestThread04_1 implements Runnable{

    //票数
    private int ticketNumber = 10;

    @Override
    public void run() {
        while(true){
            if (ticketNumber<=0){
                break;
            }
            //模拟延迟一段时间（延时）
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->拿到了第"+ticketNumber--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread04_1 ticket = new TestThread04_1();

        new Thread(ticket,"马里奥").start();
        new Thread(ticket,"路易").start();
        new Thread(ticket,"黄牛党").start();
    }
}
