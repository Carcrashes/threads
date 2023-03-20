package com.thread.class2;

/**
 * @ClassName Thread7JMMLock
 * @Description lock 解决
 * @Author dingyu
 * @Date 2023/3/20 20:58
 * @Version 1.0
 **/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock实现内存可见性原理：
 *    lock实际利用了volatile关键字，因为lock在加锁和释放锁的时候，cas时候实际上是操作state变量，state 用volatile关键字修饰，
 *
 */
public class Thread7JMMLock {

    static  boolean flag=true;

    private static Lock lock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while (flag){
                lock.lock();
                try {

                }finally {
                    lock.unlock(); //防止死锁，一定需要释放锁
                }
            }
            System.out.println("th1 thread end");
        });
        thread.start();
        Thread.sleep(5000);
        flag=false;
    }
}
