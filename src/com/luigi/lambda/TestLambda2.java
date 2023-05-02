package com.luigi.lambda;

public class TestLambda2 {

    public static void main(String[] args) {

//        Ilove love = null;

        //1.带参数的lambda表达式
        Ilove love = (String name)->{
            System.out.println("I love you-->" + name);
        };
        love.loveFunc("马里奥");

        //2.简化1的参数类型
        Ilove love2 = ( name )->{
            System.out.println("I love you-->" + name);
        };
        love2.loveFunc("路易吉");

        //3.简化2，去掉括号
        Ilove love3 = name -> {
            System.out.println("I love you-->" + name);
        };
        love3.loveFunc("奇诺比奥");

        //4.简化3，去掉花括号
        Ilove love4 = name -> System.out.println("I love you-->" + name);
        love4.loveFunc("奇诺比珂");

        //总结：
            //lambda表达式只能有一行代码的情况下才能简化成为一行，如果有多行，那么就用代码块包裹。
            //前提是接口为函数式接口
        //多个参数也可以去掉参数类型，要去掉就都去掉，必须加上括号
    }

}


interface Ilove{
    void loveFunc(String name);
}

/*
class Love implements Ilove{
    @Override
    public void loveFunc(String name) {
        System.out.println("I love you1");
    }
}*/
