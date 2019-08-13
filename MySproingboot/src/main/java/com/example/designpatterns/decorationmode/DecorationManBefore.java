package com.example.designpatterns.decorationmode;

/**
 * @author jemmy
 * @Auther: jemmy
 * @Date: 2019/6/25 14:29
 * @Description:装饰者模式抽象具体类
 */
public class DecorationManBefore extends  DecorationMode {


    public DecorationManBefore(Person person) {
        super(person);
    }

    public DecorationManBefore() {
    }

    @Override
    public void eat() {
        beforeEat();
        super.eat();
    }
    private void beforeEat(){
        System.out.println("吃饭之前先锻炼");
    }
}
