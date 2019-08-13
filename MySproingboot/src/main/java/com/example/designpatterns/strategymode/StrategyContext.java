package com.example.designpatterns.strategymode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 10:11
 * @Description:收费方法策略 策略模式是对一系列变化的封装，所有的变化都是完成同一个工作，只是实现不同，以相同的方式调用所有的算法，
 * 降低算法与使用算法之间的耦合
 */
public class StrategyContext {

    CashSuper cs =  null;

    public StrategyContext(String type) {

       switch (type){
           case "正常收费" :
               cs=new NomalCash();
           break;
           case "满300返100" :
               cs=new ReturnCash(100,300);
               break;
           case "打八折" :
               cs=new DiscountCash(0.8);
               break;
           default:
               cs=new NomalCash();
               break;
       }

    }

    public double cash( double money){
       return cs.cashMonery(money);
    }

}
