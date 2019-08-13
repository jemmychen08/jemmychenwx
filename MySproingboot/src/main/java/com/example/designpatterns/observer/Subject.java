package com.example.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 14:29
 * @Description: 被观察者抽象类
 */
public abstract class Subject {
    private List<ObserverAbstact>  ObserverList = new ArrayList<>();

    public void addObser(ObserverAbstact observerAbstact){
        ObserverList.add(observerAbstact);
    }

    public void dettach(ObserverAbstact observer) {
        ObserverList.remove(observer);
    }
    /**
     *
     * @Description:
     *
     * @auther: jemmy_chen
     * @param: []
     * @return: void
     * @date: 2019/6/27 15:06
     */
    public void notifyObserver() {
        for (ObserverAbstact observer : ObserverList) {
            observer.update("灰太狼要搞事情了");
        }

    }
}
