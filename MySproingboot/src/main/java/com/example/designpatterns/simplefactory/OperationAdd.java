package com.example.designpatterns.simplefactory;


/**
 * @Auther: jemmy
 * @Date: 2019/6/19 14:48
 * @Description:加法运算类
 */
public class OperationAdd extends Operation {
    @Override
    public double getRusult(){
        return numA + numB;
    }
}
