package com.example.designpatterns.strategymode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 10:00
 * @Description: 打折收费
 */
public class DiscountCash extends CashSuper{

    private  double discount;
    /**
     *
     * @Description:初始化的时候传入折扣
     *
     * @auther: jemmy_chen
     * @param: [discount]
     * @return:
     * @date: 2019/6/25 10:03
     */
    public DiscountCash(double discount) {
        this.discount = discount;
    }

    @Override
    public double cashMonery(double money) {
        return money * discount;
    }
}
