package com.example.designpatterns.simplefactory;

import java.util.Scanner;

/**
 * @Auther: jemmy
 * @Date: 2019/6/19 14:38
 * @Description:计算器（简单工厂模式）客户端
 */
public class CalculatorCustomer {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        int numA = sc.nextInt();
        System.out.println("请输入第二个数字：");
        int numB = sc.nextInt();
        System.out.println("请输入运算符号：");
        String opera = sc.next();

        Operation operation = OperationFactory.createOperation(opera);

        operation.setNumA(numA);
        operation.setNumB(numB);
        double rusult = operation.getRusult();
        System.out.println("运算结果为："+rusult);

    }
}
