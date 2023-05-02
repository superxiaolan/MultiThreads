package com.luigi.DeadLock;

//死锁，多个线程互相抱着对方需要的资源，然后形成僵持.
public class DeadLock {
    public static void main(String[] args) {
        Makeup girl1 = new Makeup(0,"碧琪公主");
        Makeup girl2 = new Makeup(1,"库霸王");

        girl1.start();
        girl2.start();

    }
}

//口红
class Lipstick{

}

//镜子
class Mirror{

}

class Makeup extends Thread{
    //需要的资源只有一份，用static来保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    @Override
    public void run() {
        //化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    int choice; //选择,0表示拿到镜子，1表示拿到口红
    String girlName; //使用化妆品的人

    Makeup(int choice,String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }

    //化妆，相互持有对方的锁，就是需要拿到对方的资源
    private void makeup() throws InterruptedException {
        if (choice==0){
            synchronized(lipstick){//获得口红的锁
                System.out.println(this.girlName+"获得口红的锁");
                Thread.sleep(1000);
            }
            synchronized (mirror){//一秒钟后想获得镜子的锁
                System.out.println(this.girlName+"获得镜子的锁");
            }
        }
        else {
            synchronized(mirror){//获得镜子的锁
                System.out.println(this.girlName+"获得镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipstick){//一秒钟后想获得口红的锁
                System.out.println(this.girlName+"获得口红的锁");
            }
        }

    }
}