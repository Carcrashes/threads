package com.thread.class1;

/**
 * @ClassName MainWaitAndNotifyTest
 * @Description 线程的等待和唤醒
 * @Author dingyu
 * @Date 2023/3/5 22:20
 * @Version 1.0
 **/
public class Main5WaitAndNotifyTest {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 1、可以让获取了synchronized锁资源的线程通过wait方法进入到等待池中，并且会释放锁资源
         * 2、可以让获取synchronized锁资源的线程，通过notify 或者 notifyAll 方法，将等待池中线程唤醒。添加到锁池中（等待获取synchronized锁资源）
         * 3、notify ：随机唤醒等待池中一个线程到锁池中
         * 4、notifyAll： 唤醒所有等待池中线程到锁池中
         * 5、注意点：调用notify 或者notifyAll时候，比如在synchronized修饰的方法下才可以，因为要操作某个对象锁的信息维护
         */
        Thread th1=new Thread(()->{
            try {
                sync();//同步方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread-test-1");

        Thread th2=new Thread(()->{
            try {
                sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread-test-2");
        th1.start();
        th2.start();
        Thread.sleep(12000);
        synchronized (Main5WaitAndNotifyTest.class){
            //MainWaitAndNotifyTest.class.notify(); //唤醒某个线程，此时程序不会结束，未唤醒的线程还在等待池中。
            Main5WaitAndNotifyTest.class.notifyAll(); //唤醒synchronized 对象锁下的的所有线程。等待CPU调度
        }





    }

    //synchronized 是静态方法，此时锁对象为当前类MainWaitAndNotifyTest
    public  static synchronized void sync() throws InterruptedException {
        for (int a = 0; a <10 ; a++) {
            if (a==2){
                Main5WaitAndNotifyTest.class.wait(); //调用wait方法释放锁，进入到等待池，正常就是会停止12s，然后当时下的通过主线程唤醒synchronized锁对象下的一个或者线程进入到锁池去等待CPU调度
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+":"+a);
        }
    }
}
