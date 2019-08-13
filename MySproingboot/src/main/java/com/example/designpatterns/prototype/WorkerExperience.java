package com.example.designpatterns.prototype;

/**
 * @Auther: jemmy
 * @Date: 2019/6/26 09:19
 * @Description: 浅复制不实现Cloneable接口
 *
 */
public class WorkerExperience implements  Cloneable{

    String companyname;

    public WorkerExperience() {
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
