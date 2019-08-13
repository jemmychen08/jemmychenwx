package com.example.designpatterns.strategymode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 10:17
 * @Description:策略模式客户端
 */
public class CashCustomer {

    public static void main(String[] args) {
        StrategyContext sc = new StrategyContext("正常收费");
        double cash = sc.cash(300);
        System.out.println(cash);
        StrategyContext sc1 = new StrategyContext("满300返100");
        double cash1 = sc1.cash(300);
        System.out.println(cash1);
        StrategyContext sc2 = new StrategyContext("打八折");
        double cash2 = sc1.cash(300);
        System.out.println(cash2);
    }

}
