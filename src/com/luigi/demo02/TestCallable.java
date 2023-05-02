package com.luigi.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

//线程创建方式三：实现callable接口
/*
*callable 接口的好处：
* 1.可以定义返回值.
* 2.可以抛出异常.
* */
//
public class TestCallable implements Callable<Boolean> {
    private String url;
    private String name;

    public TestCallable(String url,String name) {
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public Boolean call() {
        //run()线程方法体
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downloader(url,name);
        System.out.println("下载了文件名为 "+ name + "的图片");
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/09/08/kuangstudya9f1a304-0940-45b7-9e6d-f1d4b46a155c.jpg","1");
        TestCallable t2 = new TestCallable("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/07/21/kuangstudyb62b0ccb-55b5-4572-b067-347314beac15.jpg","2");
        TestCallable t3 = new TestCallable("https://kuangstudy.oss-cn-beijing.aliyuncs.com/bbs/2021/07/21/kuangstudy69ec9992-bed7-4eec-a550-5fe2aeb44737.jpg","3");

        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        //获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        //关闭服务
        ser.shutdownNow();
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