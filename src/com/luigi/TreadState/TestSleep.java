package com.luigi.TreadState;


//模拟网络延时，放大问题的发生性
public class TestSleep implements Runnable{

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
        TestSleep ticket = new TestSleep();

        new Thread(ticket,"马里奥").start();
        new Thread(ticket,"路易").start();
        new Thread(ticket,"黄牛党").start();
    }
}
