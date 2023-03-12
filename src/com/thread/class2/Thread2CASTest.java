package com.thread.class2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName CASThreadTest
 * @Description CAS ：compare and swap 比较与交换
 * @Author dingyu
 * @Date 2023/3/9 16:43
 * @Version 1.0
 **/
public class Thread2CASTest {

    /**
     * 1、CAS是CPU并发原语，表示再替换内存中某些值时候，需要先查看内存中的值与预期的值是否一致，一致则进行替换，不一致就不进行替换。 cas这个操作是原子性的
     * 2、Java是基于Unsafe类，提供对CAS的操作方法，JVM实现了cas的汇编指令
     * 3、CAS只是实现了比较和交换，在获取原子这个操作需要自己进行
     * 4、AtomicXXXX就是利用CAS并发下原子性。
     * 缺点：CAS只能保证一个变量的操作是原子性的，无法实现对多行代码实现原子性
     */

    private static  AtomicInteger num=new AtomicInteger(0);

    /**
     *   public final int incrementAndGet() {
     *         return unsafe.getAndAddInt(this, valueOffset, 1) + 1;  //利用Unsafe类实现incrementAndGet
     *   }
     *
     *   利用CAS循环判断是否是预期值，
     *   public final int getAndAddInt(Object var1, long var2, int var4) {
     *         int var5;
     *         do {
     *             var5 = this.getIntVolatile(var1, var2);
     *
     *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));  //利用Unsafe实现CAS操作  var1 表示当前对象，var2 表示偏移量之后需要修改的对象，var5 表示预期值，var5+var4 表示需要替换的值
     *         return var5;
     *     }
     *
     *
     *    public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5); compareAndSwapInt 是原子性的。
     *
     */
//    public static void main(String[] args) throws InterruptedException {
//        Thread th1=new Thread(()->{
//            for (int i = 0; i < 100; i++) {
//                num.incrementAndGet();
//            }
//
//        },"th1");
//        Thread th2=new Thread(()->{
//            for (int i = 0; i < 100; i++) {
//                num.incrementAndGet();
//            }
//        },"th2");
//        th1.start();
//        th2.start();
//        th1.join(); //Main休眠 回到就绪状态 等待th1 完成
//        th2.join(); //等待th2 完成
//        System.out.println(num.get());
//    }


         /* 问题1 ： ABA问题，
            *  1、线程A获取值A处理后变成B未更新到主存，
            *  2、然后线程B从主存获取值A操作，线程休眠或者挂起之后，
            *  3、线程C也从主存获取值B，然后进行处理变成A
            *  4、线程A将值同步主存中，此时主存是B，然后C线程先将主存B到修改为A。此时B线程也同步比较发现一致。进行更新。实际上是不一致的。
            * 解决办法，可以利用AtomicStampedReference 处理
            *   AtomicStampedReference 在进行比较替换时候，不仅会比较值，还会比较版本信息
            *
         */
//    public static void main(String[] args) {
//        AtomicStampedReference<String> num=new AtomicStampedReference<>("AAA",1);
//
//        String oldValue= num.getReference();
//        Integer oldVersion=num.getStamp();
//
//        num.compareAndSet(oldValue,"B",oldVersion,oldVersion+1);
//        System.out.println("修改1后的版本值："+num.getReference());
//
//        num.compareAndSet("B","C",2,1+1);  //expectedStamp=1 修改失败，此时预期值为2 ，expectedStamp=2 时候，修改成功
//        System.out.println("修改2后的版本值："+num.getReference());
//    }

    /**
     * 问题2：自旋时间过长的问题：
     * 1、可以指定自旋次数，超过这个次数，直接失败或者挂起（自旋锁/自适应锁）
     * 2、可以在CAS失败一次后，将这个操作暂存，当需要获取值时候，将暂存的操作执行完，在返回最后的结果
     */


}
