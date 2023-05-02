package com.luigi.ProducerConsumer;


//测试：生产者消费者模型-->利用缓冲区解决：管程法
//生产者，消费者，产品，缓冲区
public class TestProducerConsumer {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Producer(container).start();

        new Consumer(container).start();
    }
}

//生产者
class Producer extends Thread{
    SynContainer container;

    public Producer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Mushroom(i));
            System.out.println("生产了第-->" + i + "个蘑菇");
        }
    }
}

//消费者
class Consumer extends Thread{
    SynContainer container;

    public Consumer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("消费了第-->" + container.pop().id+"个蘑菇");
        }
    }
}

//产品：资源
class Mushroom{

    int id;

    public Mushroom(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

//缓冲区
class SynContainer{

    //需要一个容器大小
    Mushroom[] mushrooms = new Mushroom[10];
    //容器计数器
    int count = 0;

    //生产者放入产品（蘑菇）
    public synchronized void push(Mushroom mushroom){
        //如果容器满了，就需要等待消费者消费
        //if (count==mushrooms.length){
        if (count==10){
            //通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果没有满，我们就需要丢入产品
        mushrooms[count] = mushroom;
        count++;

        //System.out.println("生产了第-->" + mushroom.getId() + "个蘑菇");
        //可以通知消费者消费了
        this.notifyAll();
    }

    //消费者消费产品
    public synchronized Mushroom pop(){
        //判断能否消费
        if (count==0){
            //等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果可以消费
        count--;
        Mushroom mushroom = mushrooms[count];
        //System.out.println("消费了第-->" + mushroom.getId()+"个蘑菇");
        //吃完了，通知生产者生产
        this.notifyAll();
        return mushroom;
    }
}