package com.example.designpatterns.template;

import javax.sound.midi.Soundbank;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 16:02
 * @Description:
 */
public class PotatoBurdock extends CookeTemplate {
    @Override
    void prepar() {
        System.out.println("切好土豆，切好牛腩");
    }

    @Override
    void cooke() {
        System.out.println("加食用油,超好吃的土豆牛腩出锅");
    }

    @Override
    void carry() {
        System.out.println("客人闻到牛肉的香味流口水");
    }
}
