package com.example.designpatterns.decorationmode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 14:27
 * @Description: 装饰者抽象类  动态地给一个对象添加一些额外的职责。
 * 就增加功能来说， Decorator模式相比生成子类更为灵活。该模式以对客户端透明的方式扩展对象的功能。
 * 此处就是给具体的男人吃饭功能 增加吃饭前 吃饭后的动作
 */
public abstract class DecorationMode implements  Person {

    Person person;

    public DecorationMode(Person person) {
        this.person = person;
    }

    public DecorationMode() {
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void eat() {
        person.eat();
    }


}
