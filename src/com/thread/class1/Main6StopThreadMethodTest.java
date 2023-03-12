package com.thread.class1;

/**
 * @ClassName MainStopThreadMethodTest
 * @Description 停止线程方法 stop /共享变量需要使用volatile关键字保证可见性 /interrupt
 * @Author dingyu
 * @Date 2023/3/5 22:38
 * @Version 1.0
 **/
public class Main6StopThreadMethodTest {

    static volatile boolean flag=true;

    public static void main(String[] args) throws InterruptedException {
        //1、stop 方法 强制停止线程、立马停止，不推介使用
//        Thread th1=new Thread(()->{
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        th1.start();
//        Thread.sleep(500);
//        th1.stop(); //强制停止线程。立马停止
//        System.out.println(th1.getState());

        //2、共享变量方式
//        Thread th1=new Thread(()->{
//            //flag 变量开始一直为true，main线程修改为false,线程结束。volatile 关键字保证变量的可见性
//            while (flag){
//
//            }
//            System.out.println("任务结束");
//        });
//        th1.start();
//        Thread.sleep(5000);
//        flag=false; //修改为false

        //3、interrupt方式
        //3.1 共享变量方式 默认情况下 interrupt标记位：false
//        System.out.println(Thread.currentThread().isInterrupted()); //返回interrupt标识位
//        Thread.currentThread().interrupt(); //执行interrupt 进行打断，interrupt标志位变为true
//        System.out.println(Thread.currentThread().isInterrupted());
//        System.out.println(Thread.interrupted()); //返回当前线程interrupt标志位，并且归位为false;
//        System.out.println(Thread.currentThread().isInterrupted());
//        Thread th1 = new Thread(() -> {
//            //flag 变量开始一直为true，main线程修改为false,线程结束。volatile 关键字保证变量的可见性
//            while (!Thread.currentThread().isInterrupted()) {
//
//            }
//            System.out.println("任务结束");
//        });
//        th1.start();
//        Thread.sleep(5000);
//        th1.interrupt();

        //4、通过打断WAIT或者SLEEP_WAIT状态的线程，然后自行抛出异常进行处理，这是常见的停止线程方式，在JUC和框架中比较常见
        Thread th1=new Thread(()->{
            while (true){
                //1、获取任务
                //2、处理任务
                //3、没有任务情况下，通过wait或者sleep方式让线程睡眠。
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("基于打断方式结束当前线程");
                    return;
                }
            }
        });
        th1.start();
        Thread.sleep(1000);
        th1.interrupt(); //打断th1线程，然后捕获异常，结束线程
    }
}
