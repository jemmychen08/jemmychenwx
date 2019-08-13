package com.example.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 10:50
 * @Description:JDK 动态代理
 */
public class DynProxyLawyer implements InvocationHandler {
    /**
     * 被代理的对象
     */
    private Object target;
    public DynProxyLawyer(Object obj){
        this.target=obj;
    }
@Override
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("案件进展：" +method);
        Object invoke = method.invoke(target, args);
        return invoke;
        }
        }
