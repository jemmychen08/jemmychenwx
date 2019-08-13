package com.example.designpatterns.observer;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 15:11
 * @Description:
 */
public class ObserClient {
    public static void main(String[] args) {
        Wolf wolf = new Wolf();


        ObserverImpl1 observerImpl1 = new ObserverImpl1();
        wolf.addObser(observerImpl1);

        ObseverImpl2 obseverImpl2 = new ObseverImpl2();
        wolf.addObser(obseverImpl2);
        wolf.gaoshi();
    }

}
