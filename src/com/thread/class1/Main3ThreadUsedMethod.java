package com.thread.class1;

/**
 * @ClassName MainThreadUsedMethod
 * @Description 线程常用方法
 * @Author dingyu
 * @Date 2023/3/5 17:05
 * @Version 1.0
 **/
public class Main3ThreadUsedMethod {

    public static void main(String[] args) {
        //1.1获取当前线程
        //Thread thread = Thread.currentThread();
        //System.out.println(thread);  //Thread[" + getName() + "," + getPriority() + "," +group.getName() + "]"  ->  Thread[main,5,main]

        //1.2 获取线程名称(获取/设置)
        //获取
//        String name = Thread.currentThread().getName();
//        System.out.println("thread name:"+name);
//        //设置
//        Thread th=new Thread(()->{
//            System.out.println("th"+Thread.currentThread().getName());
//        });
//        th.setName("模块-功能-计数器");
//        th.start();

        //1.3 线程的优先级 CPU调度的优先级，java中给线程优先级设置1-10 ，默认普通线程是5。超过这个范围会报错
//        Thread th1=new Thread(()->{
//            for (int i = 0; i < 1000; i++) {
//                System.out.println("t1:"+i);
//
//            }
//        });
//
//        Thread th2=new Thread(()->{
//            for (int i = 0; i < 1000; i++) {
//                System.out.println("t2:"+i);
//
//            }
//        });
//        th1.setPriority(2); //设置t1的优先级
//        th2.setPriority(10); //设置t2的优先级
//        th2.start(); //先启动th2
//        th1.start(); //启动th1
        //这时候不一定th1 一定会比th2先执行，比如th2启动立马获取CPU资源开始执行

        //1.4 线程的让步，可以通过Thread的静态方法yield，让当前线程运行状态转换为就绪状态
//        Thread th1=new Thread(()->{
//            for (int i = 0; i < 1000; i++) {
//                if (i==50){
//                    Thread.yield();
//                }
//
//                System.out.println("th1:"+i);
//            }
//        });
//
//        Thread th2=new Thread(()->{
//            for (int i = 0; i < 1000; i++) {
//                System.out.println("th2:"+i);
//            }
//        });
//        th1.start();
//        th2.start();

        //1.5 线程休眠状态  Thread的静态方法，可以让线程从运行状态转换为等待状态
        //sleep有两个方法重载： 1、native 方法 修饰，让线程转为等待状态的效果。 2、传入一个毫秒和纳秒（纳秒>5000，就给毫秒值+1）
//        try {
//            Thread.sleep(5000);
//            Thread.sleep(5000,6000); //>5000 睡眠时间6s
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //1.6 线程的强占，Thread 非静态方法join，所以join需要在某个线程实例下调用 th.join()等待th线程执行完毕，th.join(2000) 等待th线程2s。如果th在2s之前结束。等待线程会立马恢复
        // main 方法中调用了th.join ，那么main线程会进入到等待状态，需要等待th1线程执行完毕，再恢复到就绪状态。等待CPU调度
        // main 方法中调用了th.join(2000)，那么main线程会进入到等待状态，需要等待th执行2s后，再恢复到就绪状态，如果th在main线程等待期间，th提前结束，main线程自动变为就绪状态
//        Thread th1=new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                System.out.println("th1:"+i);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        //
//        th1.start();
//        for (int i = 0; i <10 ; i++) {
//            System.out.println("main:"+i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (i==2){
//                try {
//                    th1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }



    }
}
