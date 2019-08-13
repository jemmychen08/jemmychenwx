package com.example.designpatterns.proxy;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 11:32
 * @Description:
 */
public class ProxyClient {
    public static void main(String[] args) {
        ILawSuit staticProxy = ProxyFactory.createStaticProxy(new Plaintiff1());
        staticProxy.sumit("合同");
        staticProxy.defind();
        ILawSuit dynProxy = (ILawSuit) ProxyFactory.createDynProxy(new Plaintiff2());
        dynProxy.sumit("流水账单");
        dynProxy.defind();
    }
}
