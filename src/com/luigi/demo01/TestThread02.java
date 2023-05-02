package com.luigi.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//创建线程方式之一，继承Thread类
public class TestThread02 extends Thread{

    private String url;
    private String name;

    public TestThread02(String url,String name) {
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public void run() {
        //run()线程方法体
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downloader(url,name);
        System.out.println("下载了文件名为 "+ name + "的图片");
    }

    public static void main(String[] args) {
        TestThread02 t1 = new TestThread02("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/09/08/kuangstudya9f1a304-0940-45b7-9e6d-f1d4b46a155c.jpg","1");
        TestThread02 t2 = new TestThread02("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/07/21/kuangstudyb62b0ccb-55b5-4572-b067-347314beac15.jpg","2");
        TestThread02 t3 = new TestThread02("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/07/21/kuangstudy69ec9992-bed7-4eec-a550-5fe2aeb44737.jpg","3");

        t1.start();
        t2.start();
        t3.start();
    }
}

//下载器
class WebDownLoader{


    //下载方法
    public void downloader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法异常" + name);
        }
    }

}
