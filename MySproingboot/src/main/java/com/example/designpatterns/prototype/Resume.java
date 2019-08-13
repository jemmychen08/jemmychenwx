package com.example.designpatterns.prototype;


/**
 * @Auther: jemmy
 * @Date: 2019/6/26 08:58
 * @Description:简历类
 */
public class Resume implements Cloneable {
    String name ;
    String age;
    WorkerExperience workerExperience;

    public Resume() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public WorkerExperience getWorkerExperience() {
        return workerExperience;
    }

    public void setWorkerExperience(WorkerExperience workerExperience) {
        this.workerExperience = workerExperience;
    }

    @Override
    protected Object clone() {
        Object proto = null;
        try {
            proto =  super.clone();
            //深复制加这一段代码
            Resume person = (Resume) proto;
            person.workerExperience = (WorkerExperience) this.workerExperience.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return proto;
    }

    @Override
    public String toString() {
        return age+"岁的"+name+"在"+workerExperience.companyname +"工作";
    }
}
