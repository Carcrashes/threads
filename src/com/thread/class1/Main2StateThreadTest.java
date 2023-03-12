package com.thread.class1;

/**
 * @ClassName MainStateThreadTest
 * @Description 线程状态
 * @Author dingyu
 * @Date 2023/3/2 20:43
 * @Version 1.0
 **/
//JAVA 定义了6种线程状态 NEW   RUNNABLE  BLOCKED  WAITING  TIMED_WAITING  TERMINATED
//NEW 表示新建线程状态  RUNNABLE 表示获取CPU资源执行状态  BLOCKED 阻塞状态，通常是synchronized未获取锁资源   WAITING 等待状态，调用wait方法之后，线程需要手动唤醒
// TIMED_WAITING  时间等待状态,时间结束后，线程会自动唤醒   TERMINATED 结束状态
// BLOCKED  WAITING  TIMED_WAITING 阻塞，CPU不会调度当前线程
public class Main2StateThreadTest {

    public static void main(String[] args) throws InterruptedException {
        //new 状态
        //Thread thread=new Thread(()->{});
        //System.out.println(thread.getState());

        //RUNNABLE 状态
//        Thread thread=new Thread(()->{
//            while (true){
//
//            }
//        });
//        thread.start();//CPU调度
//        Thread.sleep(500);//主线程 阻塞500ms，这时候thread 肯定启动了
//        System.out.println(thread.getState());


          //BLOCKED 状态
//          Object obj=new Object();
//          Thread thread=new Thread(()->{
//              //thread 无法拿到锁，导致变成了BLOCKED状态
//              synchronized (obj){
//
//              }
//          });
//          synchronized (obj){
//              thread.start(); //启动thread 会尝试获取锁，此时锁已经被Main持有
//              Thread.sleep(500);
//              System.out.println(thread.getState());
//          }

        //WAITING 通过wait方法
//        Object obj=new Object();
//        Thread thread=new Thread(()->{
//            synchronized (obj){
//                try {
//                    obj.wait(); //调用wait方法，讲线程休眠
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//        Thread.sleep(500);
//        System.out.println(thread.getState());

        //TIMED_WAITING
//        Object obj=new Object();
//        Thread thread=new Thread(()->{
//            synchronized (obj){
//                try {
//                   Thread.sleep(1000); //调用sleep方法，讲线程休眠,时间结束会自动恢复
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//        Thread.sleep(500);
//        System.out.println(thread.getState());

        //TIMED_WAITING
        Object obj=new Object();
        Thread thread=new Thread(()->{
            synchronized (obj){
                try {
                    Thread.sleep(100); //调用wait方法，讲线程休眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(500); //阻塞线程。然后保证子线程先结束
        System.out.println(thread.getState());
    }


}




