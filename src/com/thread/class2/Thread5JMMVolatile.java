package com.thread.class2;

/**
 * @ClassName Thread5JMMVIew
 * @Description 内存可见性
 * @Author dingyu
 * @Date 2023/3/20 20:34
 * @Version 1.0
 **/

/**
 * 产生原因： JMM规定变量都存储到主存中，CPU运行时候，都从主存中复制到内核中，但是CPU直接从主存中获取数据，CPU的速度远远比内存中快，所以CPU在主存之间存在一个三级缓存，L1 L2 L3，CPU优先从缓存中读取数据，不会直接从主存中获取数据
 * 解决办法：
 * 1、volatile 关键字：修饰变量，转为汇编之后指令之后，在前缀加了Lock，告诉CPU，不能从缓存中操作数据，只能从主存中获取数据。
 * 内存语义：
 * 读操作：JMM会将该变量在其他CPU中的缓存设置为无效，必须从主存中获取变量
 * 写操作：JMM会将该变量对应的CPU缓存立马刷新到主存中，
 *
 * 2、volatile关键字修饰变量的变量被操作之后，会将该变量和其他的属性立即同步到缓存中，还会将其他CPU缓存的数据设置为无效，lock实现可见性，利用volatile关键字上。
 *
 *
 */
public class Thread5JMMVolatile {

    private static volatile boolean flag=true;

    public static void main(String[] args) throws InterruptedException {
        Thread th=new Thread(()->{
           while (flag){
               //读取任务
               //操作任务
           }
            System.out.println("程序结束");

        });
        th.start();
        Thread.sleep(5000);
        System.out.println("main线程将flag修改为：false，停止掉th线程");
        flag=false; // volatile 保证变量flag在内存中的可见性

    }

}
