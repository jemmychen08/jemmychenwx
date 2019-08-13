package com.example.designpatterns.simplefactory;

/**
 * @Auther: jemmy
 * @Date: 2019/6/19 15:02
 * @Description:  即应用程序将对象的创建及初始化职责交给工厂对象。
 */
public class OperationFactory {


    public static Operation createOperation(String operation) {
        Operation oper = null;
        switch (operation){
            case "+":
                oper = new OperationAdd();
                break;
            case "-":
                oper = new OperationSub();
                break;
            default:
                oper = new OperationAdd();
                break;
        }
        return oper;
    }
}
