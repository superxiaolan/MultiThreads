package com.luigi.TreadState;


//测试stop
//1.建议线程正常停止-->利用次数，不建议死循环。
//2.建议用标志位-->设置一个标志位。
//3.不要使用Thread类里自带的stop函数或者destroy函数这些过时抛弃的方法，也不要使用JDK不建议使用的方法。
public class TestStop implements Runnable{
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;

        while (flag){
            System.out.println("run ... Thread " + i++);
        }
    }

    //2.设置一个公开的方法停止线程，转换标志位
    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();

        new Thread(testStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main...Thread "+ i);
            if ( i == 900 ){
                //调用stop方法切换标志位，让线程停止
                testStop.stop();
                System.out.println("testStop线程停止了");
            }
        }
    }

}
