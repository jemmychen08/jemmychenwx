package com.example.designpatterns.proxy;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 09:37
 * @Description:原告1，被代理对象
 */
public class Plaintiff1 implements ILawSuit {


    @Override
    public void sumit(String evidence) {
        System.out.println("原告1提供证据："+evidence);
    }

    @Override
    public void defind() {
        System.out.println("原告1诉讼请求：还钱");
    }
}
