package com.example.designpatterns.template;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 16:04
 * @Description:
 */
public class TemplateClient {

    public static void main(String[] args) {

        TomatoEggs tomatoEggs = new TomatoEggs();
        tomatoEggs.process();

        PotatoBurdock potatoBurdock = new PotatoBurdock();
        potatoBurdock.process();
    }

}
