package com.luigi.demo03;

//学习静态代理
//静态代理模式总结：
//真实对象和代理对象都要实现同一个接口
//代理对象要代理真实角色

/*
    代理对象可以做很多真实对象做不了的事情
    真实对象专注于做自己的事情
 */
public class StaticProxy {

    public static void main(String[] args) {

        /*
           如果没用到代理模式，以往常规是这样的
         */
        /*You you = new You();
        you.HappyMarry();
        */

        new Thread( ()-> System.out.println("我爱你")).start();

        new WeddingCompany(new You()).HappyMarry();


        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.HappyMarry();

    }
}

interface Marry{

    void HappyMarry();
}

//真实角色，你去结婚
class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("马里奥救到公主了，超级开心");
    }
}

class WeddingCompany implements Marry{
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {

        before();
        this.target.HappyMarry();
        after();
    }

    private void before(){
        System.out.println("结婚之前，布置现场");
    }

    private void after(){
        System.out.println("结婚之后，收尾款");
    }
}