package com.example.designpatterns.template;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 15:58
 * @Description:
 */
public class TomatoEggs extends CookeTemplate {
    @Override
    void prepar() {
        System.out.println("切好西红柿，打好鸡蛋");
    }

    @Override
    void cooke() {
        System.out.println("加食用油,一会炒好，香喷喷");
    }

    @Override
    void carry() {
        System.out.println("客人等不及了，要吃西红柿鸡蛋！");
    }
}
