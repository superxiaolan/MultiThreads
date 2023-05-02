package com.luigi.sync;

import java.util.ArrayList;
import java.util.List;

public class UnsafeList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 10000; i++) {
            new Thread(
                    ()->{
                        synchronized (list){
                            list.add(Thread.currentThread().getName());
                        }
                        //list.add(Thread.currentThread().getName());
                    }
            ).start();

            //模拟延时，放大问题
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(list.size());
        //运行后发现并没有100个线程添加进list里，为什么呢？因为线程不安全
    }
}
