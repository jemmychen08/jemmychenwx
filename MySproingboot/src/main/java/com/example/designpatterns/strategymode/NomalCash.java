package com.example.designpatterns.strategymode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 09:58
 * @Description:正常收费
 */
public class NomalCash extends CashSuper {

    public NomalCash() {
    }

    @Override
    public double cashMonery(double money) {
        return money;
    }
}
