package com.luigi.TreadState;

public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);//默认是false，表示是用户线程，正常的线程都是用户线程...

        thread.start();//上帝守护线程启动

        new Thread(you).start();
    }
}

//上帝
class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("上帝守护着你");
        }
    }
}

class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都过得无忧无虑");
        }
        System.out.println("你离开了这个世界=======再见了===");
    }
}