package com.example.designpatterns.simplefactory;


/**
 * @Auther: jemmy
 * @Date: 2019/6/19 15:00
 * @Description:
 */
public class OperationSub extends Operation {
    @Override
    public double getRusult() {
        return numA-numB;
    }
}
