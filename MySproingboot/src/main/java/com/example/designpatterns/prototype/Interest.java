package com.example.designpatterns.prototype;

/**
 * @Auther: jemmy
 * @Date: 2019/6/26 11:12
 * @Description:
 */
public class Interest implements  Cloneable{

    private String name;

    public Interest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Interest{" +
                "name='" + name + '\'' +
                '}';
    }
}
