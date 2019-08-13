package com.example.designpatterns.strategymode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 10:04
 * @Description: 返利收费
 */
public class ReturnCash extends CashSuper {
    private double moneyReturn ;
    private double moneyCondition ;

    /**
     *
     * @param moneyReturn 返利金额
     * @param moneyCondition 返利条件
     */
    public ReturnCash(double moneyReturn, double moneyCondition) {
        this.moneyReturn = moneyReturn;
        this.moneyCondition = moneyCondition;
    }

    @Override
    public double cashMonery(double money) {
        double result =money;
        if(money >= moneyCondition){
            result = money- Math.floor (money/moneyCondition) * moneyReturn;
        }
        return result;
    }
}
