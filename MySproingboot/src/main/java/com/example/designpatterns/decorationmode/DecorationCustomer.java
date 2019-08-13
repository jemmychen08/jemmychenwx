package com.example.designpatterns.decorationmode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 14:33
 * @Description:装饰者模式：动态地给一个对象添加一些额外的职责。
 *  就增加功能来说， Decorator模式相比生成子类更为灵活。该模式以对客 户端透明的方式扩展对象的功能。
 *  此处就是给具体的男人吃饭功能 增加吃饭前 吃饭后的动作
 */
public class DecorationCustomer {

    public static void main(String[] args) {

        Man m = new Man();

        DecorationManAfter da= new DecorationManAfter();
        da.setPerson(m);
        DecorationManBefore db = new DecorationManBefore();
        db.setPerson(da);
        db.eat();
     }
}
