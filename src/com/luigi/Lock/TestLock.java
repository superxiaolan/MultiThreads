package com.luigi.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        LockBuyTickets lockBuyTickets = new LockBuyTickets();

        new Thread(lockBuyTickets,"小明").start();
        new Thread(lockBuyTickets,"小红").start();
        new Thread(lockBuyTickets,"黄牛").start();
    }
}

//有Lock的抢票
class LockBuyTickets implements Runnable{

    int ticketNums = 10;

    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try{
                //加锁
                lock.lock();
                if (ticketNums >0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName()+"抢到了"+ticketNums--);
                }
                else {
                    break;
                }
            }finally {
                //解锁
                lock.unlock();
            }
        }
    }
}
