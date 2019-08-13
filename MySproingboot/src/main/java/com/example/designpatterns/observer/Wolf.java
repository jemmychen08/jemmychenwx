package com.example.designpatterns.observer;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 15:10
 * @Description:
 */
public class Wolf extends Subject {
    void gaoshi(){
        System.out.println("灰太狼出来了");
        notifyObserver();
    }
}
