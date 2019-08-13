package com.example.designpatterns.observer;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 14:28
 * @Description:
 */
public class ObseverImpl2 implements  ObserverAbstact {
    @Override
    public void update(String msg) {
        System.out.println("看见"+msg+",懒洋洋看到灰太狼就被抓起来了！");
    }
}
