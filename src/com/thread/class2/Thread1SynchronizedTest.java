package com.thread.class2;

/**
 * @ClassName AtomicThreadTest
 * @Description 原子性演示
 * @Author dingyu
 * @Date 2023/3/9 16:05
 * @Version 1.0
 **/

/**
 * JMM 内存模型，变量数据首先都存储到主存中，线程操作某个变量的时候将数据复制一份到线程内部，操作完成，再写回主存中（不一定写回）。
 * 此时并发场景下，多个线程操作临界资源时候，会导致出现数据不一致情况。
 * 1、并非编程-原子性：一个线程在执行的时候，操作不可以分割，不可以中断，另外一个线程也不会影响
 *   int i=0 :次操作是原子的， count++ 此操作不是原子，可以拆分分count+1 ;count=count+1;
 * 2、并发过程实现原子方式：
 *        锁、CAS、Lock
 */
public class Thread1SynchronizedTest {

    private  static  int num;

    //自增
    public static void incr(){
        num++;
    }

    //syncrohnized
    public static void  syncIncr(){
        synchronized(Thread1SynchronizedTest.class){
            num++;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 案例1 演示多线程下数据不一致情况,通过javap -c class文件，得出汇编指令
         *        0: getstatic    //从主存中获取数据
         *        3: iconst_1
         *        4: iadd    //加1操作
         *        5: putstatic    //更新到主存中
         *        8: return
         */
//
//        Thread th1=new Thread(()->{
//            for (int i=0;i<100;i++){
//                incr();
//            }
//        },"th1");
//
//        Thread th2=new Thread(()->{
//            for (int i=0;i<100;i++){
//                incr();
//            }
//        },"th2");
//        th1.start();
//        th2.start();
//        th1.join(); //Main等待th1线程完成
//        th2.join(); //Main等待th2线程完成
//        //正常情况下，num应该是200 但是实际跑下来出现num 是119 的情况。出现这种情况就是因为th1从主存中复制value到线程内部空间还未写回到主存，然后th2也访问主存复制了一个value进行操作。然后th1写回了主存，th2也写回了主存，这样数据一致性无法保证
//        System.out.println(num);


        /**
         * 案例2 通过synchronized 保证原子性。
         *     synchronized  保证同一时间点、只会有一个线程执行到临界资源
         *        4: monitorenter  //加锁，获取锁资源，才能往下执行
         *        5: getstatic     #2                  // Field num:I
         *        8: iconst_1
         *        9: iadd
         *       10: putstatic     #2                  // Field num:I
         *       13: aload_0
         *       14: monitorexit  //释放锁
         */
        Thread th1=new Thread(()->{
            for (int i=0;i<100;i++){
                syncIncr(); //调用synchronized同步方法
            }
        },"th1");

        Thread th2=new Thread(()->{
            for (int i=0;i<100;i++){
                syncIncr();
            }
        },"th2");
        th1.start();
        th2.start();
        th1.join(); //Main等待th1线程完成
        th2.join(); //Main等待th2线程完成
        System.out.println(num);



    }




}
