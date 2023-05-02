package com.luigi.ProducerConsumer;


//测试：生产者消费者问题2-->信号灯法，标志位解决
public class TestProducerConsumer2 {
    public static void main(String[] args) {
        Movie movie = new Movie();

        new Player(movie).start();
        new Watcher(movie).start();
    }
}

//生产者-->演员
class Player extends Thread{
    Movie movie;
    public Player(Movie movie){
        this.movie = movie;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if ( i % 2 == 0){
                this.movie.play("科幻片");
            }
            else {
                this.movie.play("爱国主义宣传片");
            }
        }
    }
}

//消费者-->观众
class Watcher extends Thread{
    Movie movie;
    public Watcher(Movie movie){
        this.movie = movie;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.movie.watch();
        }
    }
}

//产品-->电影
class Movie{
    //标志位flag的含义：
        //演员表演，观众等待 True
        //观众观看，演员等待 False
    String scene;
    boolean flag = true;

    //演员表演
    public synchronized void play(String scene){
        //演员等待
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //表演
        System.out.println("演员表演了：" + scene);

        //通知观众观看
        this.notifyAll();

        this.scene = scene;
        this.flag = !this.flag;
    }

    //观众观看
    public synchronized void watch(){
        //观众等待
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //观看
        System.out.println("观众观看了：" + scene);

        //通知演员表演
        this.notifyAll();

        this.flag = !this.flag;
    }
}
