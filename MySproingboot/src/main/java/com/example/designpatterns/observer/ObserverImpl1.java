package com.example.designpatterns.observer;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 14:26
 * @Description:
 */
public class ObserverImpl1 implements  ObserverAbstact {
    @Override
    public void update(String msg) {
        System.out.println("看见"+msg+",喜羊羊看见了灰太狼给了它一jio");
    }
}
