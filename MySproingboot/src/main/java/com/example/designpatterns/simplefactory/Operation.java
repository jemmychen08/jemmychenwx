package com.example.designpatterns.simplefactory;


/**
 * @Auther: jemmy
 * @Date: 2019/6/19 14:40
 * @Description:运算父类
 */
public class Operation {
    protected double numA=0;
    protected double numB=0;
    public double getRusult(){
        return 0;
    }

    public double getNumA() {
        return numA;
    }

    public void setNumA(double numA) {
        this.numA = numA;
    }

    public double getNumB() {
        return numB;
    }

    public void setNumB(double numB) {
        this.numB = numB;
    }
}
