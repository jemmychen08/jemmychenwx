package com.example.designpatterns.observer;


/**
 * @Auther: jemmy
 * @Date: 2019/6/27 14:13
 * @Description: 观察者抽象类
 */
public  interface ObserverAbstact   {
    /**
     * 更新观察者状态
     * @param msg
     */
     void update(String msg);
}
