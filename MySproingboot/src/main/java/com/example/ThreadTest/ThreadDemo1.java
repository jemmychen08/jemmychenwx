package com.example.ThreadTest;

/**
 * @Auther: jemmy
 * @Date: 2019/7/10 14:46
 * @Description:线程创建方式
 * 方法一：继承Thread类，作为线程对象存在（继承Thread对象）
 */
public class ThreadDemo1 extends  Thread {
    /**
     * 构造方法： 继承父类方法的Thread(String name)；方法
     * @param name
     */
    public ThreadDemo1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (!interrupted()){

            System.out.println(getName()+"线程执行了...");
            try {
                /**
                 * Thread.sleep(200); //线程休息2ms，进入等待池，等待cpu
                 *
                 * Object.wait()； //让线程进入等待锁池，等待锁,直到调用Object的notify或者notifyAll时，线程停止休眠
                 */
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ThreadDemo1 demo1 = new ThreadDemo1("first");
        ThreadDemo1 demo2 = new ThreadDemo1("second");

        demo1.start();
        demo2.start();
        /**
         * 常规方法，不多做介绍了，interrupted方法，是来判断该线程是否被中断。（
         * 终止线程不允许用stop方法，该方法不会施放占用的资源。所以我们在设计程序的时候，
         * 要按照中断线程的思维去设计，就像上面的代码一样）
         */
        demo1.interrupt();

    }
}
