package com.luigi.sync;


//不安全的买票
//线程不安全，有负数
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station,"社畜的我").start();
        new Thread(station,"手速快的你").start();
        new Thread(station,"万恶的黄牛们").start();
    }

}


class BuyTicket implements Runnable{

    //票
    private int ticketNums = 10;
    boolean flag = true;   //外部停止方式

    @Override
    public void run() {
        //买票
        while (flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //加上synchronized同步方法，锁的是this
    private synchronized void buy() throws InterruptedException {
//    private void buy() throws InterruptedException {
        if (ticketNums <= 0){
            flag = false;
            return;
        }

        //模拟延时，可以放大错误
        Thread.sleep(100);

        //买票
        System.out.println(Thread.currentThread().getName() + "拿到了-->" + ticketNums--);
    }
}