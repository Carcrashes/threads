package com.thread.class2;

/**
 * @ClassName Thread6JMMSychronized
 * @Description synchronized解决内存可见性问题
 * @Author dingyu
 * @Date 2023/3/20 20:48
 * @Version 1.0
 **/

/**
 * synchronized:也可以解决内存可见性问题
 * 原理：sync关键字，在涉及到同步代码块和同步方法内的变量，在获取到锁资源之后，会将内部涉及到的变量从CPU缓存中移除，必须去主存中获取。释放锁之后，立马将对应线程中的CPU缓存中数据同步到主存中，设置其他CPU的数据无效
 */
public class Thread6JMMSynchronized {

    private static boolean flag=true;

    public static void main(String[] args) throws InterruptedException {

        Thread th=new Thread(()->{
            while (flag){
                synchronized (Thread6JMMSynchronized.class){

                }
            }
            System.out.println("th1线程结束。");
        });
        th.start();
        Thread.sleep(5000);
        System.out.println("main线程将flag修改为：false，停止掉th线程");
        flag=false;  //synchnroized 在获取到锁资源之后，将内部涉及flag变量从CPU缓存移除，必须从主存中获取
    }
}
