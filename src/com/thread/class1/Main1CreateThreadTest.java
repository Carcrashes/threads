package com.thread.class1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName MainCreateThreadTest
 * @Description  创建线程
 * @Author dingyu
 * @Date 2023/3/2 20:23
 * @Version 1.0
 **/
public class Main1CreateThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main test");
         //1。调用thread 方式执行线程
//        MyJob job=new MyJob();
//        job.start();

        //2。调用runmable 方式执行线程
        //2.1  runnable接口 实现
//        MyRunnableJob runnableJob=new MyRunnableJob();
//        Thread thread=new Thread(runnableJob);
//
//        //2.2 匿名内部类
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//        //2.3 lambda 表达式
//        Thread thread2 = new Thread(() -> {
//            System.out.println(Thread.currentThread().getName());
//        });
//        thread.start();
//        thread1.start();
//        thread2.start();

        //3。调用Callable 方式执行线程，配合FutureTask使用 同步非阻塞
        MyCallable callable=new MyCallable();
        FutureTask futureTask=new FutureTask(callable); //FutureTask 实现了Runnable 子类
        Thread thread=new Thread(futureTask);
        thread.start();
        //获取返回值
        int i=(int)futureTask.get(); //获取线程返回值 此时会阻塞
        System.out.println("call job result:"+i);

    }


}

//1、创建线程方式1-继承thread （单继承）
class MyJob extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

//2、创建线程方式2-实现Runnable interface （推荐使用，多继承）
class MyRunnableJob implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"runnable");
    }
}

//3、创建线程方式3-实现Callable，同步非阻塞 可以返回值 Thread/Runnable 不会返回值
class MyCallable implements Callable{
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        int count=0;
        for (int i = 0; i < 100; i++) {
            count+=i;
        }
        return count;
    }
}