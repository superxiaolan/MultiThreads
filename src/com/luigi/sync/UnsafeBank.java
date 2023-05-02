package com.luigi.sync;

public class UnsafeBank {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100,"结婚基金");

        WithDraw you = new WithDraw(account,50,"你");
        WithDraw yourFiancee = new WithDraw(account,100,"你的未婚妻");

        you.start();
        yourFiancee.start();
    }
}

class Account{
    int balance; //余额
    String name; //卡名

    public Account(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }
}

//银行：模拟取款
class WithDraw extends Thread{//狂神为什么不用implements Runnable
    Account account; //账户
    //取了多少钱
    int drawingMoney;
    //现在手里有多少钱
    int remainingMoney;

    public WithDraw(Account account,int drawingMoney, String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;

    }

    //取钱

    //synchronized默认锁的是this.
    @Override
    public void run() {

        //锁的是变化的量，需要增删改的对象
        synchronized (account){
            if (account.balance-drawingMoney < 0){
                System.out.println(Thread.currentThread().getName()+"钱不够，取不了");
                return;
            }

            //模拟延时，放到问题
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额 = 余额 - 你取的钱
            account.balance = account.balance - drawingMoney;
            //你手里的钱
            remainingMoney = remainingMoney + drawingMoney;

            System.out.println(account.name+"余额为："+account.balance);
            //
            //Thread.currentThread().getName() ==this.getName()
            System.out.println(this.getName()+"手里的钱："+remainingMoney);
        }

    }
}