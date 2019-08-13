package com.example.ThreadTest;

/**
 * @Auther: jemmy
 * @Date: 2019/7/10 15:14
 * @Description: 方法二：实现runnable接口，作为线程任务存在
 */
public class ThreadDemo2 implements  Runnable {
    @Override
    public void run() {
        while (true){
            System.out.println("线程执行了...");
        }
    }

    public static void main(String[] args) {
        /**
         * Runnable 只是来修饰线程所执行的任务，它不是一个线程对象。想要启动Runnable对象，必须将它放到一个线程对象里
         */
        Thread thread =new Thread(new ThreadDemo2());
        thread.start();
    }
}
