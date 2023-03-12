package com.thread.class1;

/**
 * @ClassName MainThreadDaemonTest
 * @Description 守护线程
 * @Author dingyu
 * @Date 2023/3/5 22:13
 * @Version 1.0
 **/
public class Main4ThreadDaemonTest {


    public static void main(String[] args) {
        /**
         * 1，默认线程都是非守护线程
         * 2、JVM会在程序没有守护线程时候，结束到当前的JVM
         * 3、主线程默认是非守护线程，如果主线程执行结束、需要查看当前的JVM中是否有非守护线程，如果没有JVM直接停止
         */
        Thread thread=new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("测试线程");
        thread.setDaemon(true);
        thread.start();
    }
}
