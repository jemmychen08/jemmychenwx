package com.example.designpatterns.decorationmode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 14:31
 * @Description:装饰者模式抽象具体类
 */
public class DecorationManAfter extends  DecorationMode {


    public DecorationManAfter(Person person) {
        super(person);
    }

    public DecorationManAfter() {
    }

    @Override
    public void eat() {
        super.eat();
        afterEat();

    }
    private void afterEat(){
        System.out.println("吃饭之后玩游戏");
    }
}
