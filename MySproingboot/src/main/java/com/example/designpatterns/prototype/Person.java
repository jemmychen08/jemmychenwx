package com.example.designpatterns.prototype;

/**
 * @Auther: jemmy
 * @Date: 2019/6/26 11:11
 * @Description:
 */
public class Person implements Cloneable {
    private String name;
    private boolean gender;
    private Interest interest;

    public Person(String name, boolean gender, Interest interest) {
        this.name = name;
        this.gender = gender;
        this.interest = interest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", interest=" + interest +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //return super.clone();

        //直接调用object对象的clone()方法！
        Object obj = super.clone();

        //添加如下代码实现深复制(deep Clone)
        Person person = (Person) obj;
        //把属性也进行克隆！
        person.interest = (Interest)this.interest.clone();
        return obj;



    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Interest interest = new Interest("摄影");
        Person gg = new Person("gg",false,interest);
        System.out.println(gg);
        Person dxy = (Person)gg.clone();
        dxy.setName("dxy");
        dxy.setGender(true);
        dxy.interest.setName("咖啡");

        Person dxy1 = (Person)gg.clone();
        dxy1.setName("dxy1");
        dxy1.setGender(true);
        dxy1.interest.setName("游戏");

        System.out.println(dxy1);
        System.out.println(dxy);
        System.out.println(gg);

    }
}