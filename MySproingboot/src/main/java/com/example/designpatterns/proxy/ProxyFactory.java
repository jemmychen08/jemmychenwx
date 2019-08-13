package com.example.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 09:51
 * @Description:
 */
public class ProxyFactory {
    //静态代理
    static StaticProxyLawyer createStaticProxy(Plaintiff1 target){
        return new StaticProxyLawyer(target);
    }
    //动态代理
    static  Object createDynProxy(Object target){
        InvocationHandler invocationHandler = new DynProxyLawyer(target);
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
        return o;
    }
}
