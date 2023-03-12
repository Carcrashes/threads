package com.thread.class2;



import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Thread3LockTest
 * @Description Lock锁
 * @Author dingyu
 * @Date 2023/3/9 20:07
 * @Version 1.0
 **/

public class Thread3LockTest {
    /**
     * **ReentrantLock底层是基于AQS实现的，有一个基于CAS维护的state变量来实现锁的操作。**
     */
    private static ReentrantLock lock=new ReentrantLock();

    private static Integer num=0;

    public static  void lockIncr(){
        lock.lock();
        try {
            num++;
        }finally {
            lock.unlock(); //防止异常情况，死锁，最后一定要释放锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread th1=new Thread(()->{
            for (int i = 0; i < 100; i++) {
                lockIncr();
            }
        },"th1");

        Thread th2=new Thread(()->{
            for (int i = 0; i < 100; i++) {
                lockIncr();
            }
        },"th2");
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        System.out.println("num:"+num);
    }
}
