package com.thread.class2;

/**
 * @ClassName Thread4ThreadLocal
 * @Description ThreadLocal
 * @Author dingyu
 * @Date 2023/3/9 20:14
 * @Version 1.0
 **/

/**
 * threadLocal 实现原理：
 *  1、每个Thread中有一个成员变量    ThreadLocal.ThreadLocalMap threadLocals = null;
 *  ThreadLocalMap 利用entry[]实现 。一个thread 可以绑定多个ThreadLocal
 * 2、ThreadLocal像一个工具类，去操作ThreadLocalMap，将ThreadLocal变量做为ThreadLocalMap的key 对vaulue进行存取
 * 3、ThreadLocalMap 是一个弱引用，当JVM发生GC，发现对象不可达时候，就会被回收，说是为了解决内存泄露的问题
 * 4、问题：内存泄露问题
 *          如果ThreaLocal 引用失效，key因为弱引用，ThreadLocalMap会被GC的时候回收，如果线程还未被回收，就会导致内存泄露，内存中value无法被回收
 *         解决办法：使用完TheadLocal对象后，调用remove进行移除
 */
public class Thread4ThreadLocal {


    static  ThreadLocal tl1=new ThreadLocal();
    static  ThreadLocal th2=new ThreadLocal();

    public static void main(String[] args) {
        tl1.set("11111");
        th2.set("22222");
        Thread thread=new Thread(() ->{
            System.out.println("thread-th1:"+tl1.get());  //thread 无法获取ThreadLocal存储的变量
            System.out.println("thread-th2:"+th2.get());
        });
        thread.start();
        System.out.println("main-th1:"+tl1.get());
        System.out.println("main-th2:"+th2.get());
        tl1.remove();
        th2.remove(); //防止内存泄露
    }
}
