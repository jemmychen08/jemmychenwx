package com.example.designpatterns.proxy;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 09:39
 * @Description:代理律师，代理对象,静态代理
 */
public class StaticProxyLawyer implements ILawSuit {


    /**
     * 代理律师持有原告对象
     */
    private  Plaintiff1 plaintiff1;

    public StaticProxyLawyer(Plaintiff1 plaintiff1) {
        this.plaintiff1 = plaintiff1;
    }

    @Override
    public void sumit(String evidence) {
        plaintiff1.sumit(evidence);
    }

    @Override
    public void defind() {
        plaintiff1.defind();
    }

}
