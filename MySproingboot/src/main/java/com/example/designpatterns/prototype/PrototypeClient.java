package com.example.designpatterns.prototype;

/**
 * @Auther: jemmy
 * @Date: 2019/6/26 09:21
 * @Description:
 * 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
 * 深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
 */
public class PrototypeClient {

    public static void main(String[] args) {


        WorkerExperience workerExperience =new WorkerExperience();
        workerExperience.setCompanyname("MY");
        Resume rs =new Resume();
        rs.setName("陈剑");
        rs.setAge("27");
        rs.setWorkerExperience(workerExperience);

        System.out.println(rs.toString());

        Resume rs1=(Resume)rs.clone();
        rs1.setAge("28");
        rs1.workerExperience.setCompanyname("JD");

        Resume rs2=(Resume)rs.clone();
        rs2.setAge("29");
        rs2.workerExperience.setCompanyname("AL");




        System.out.println(rs.toString());
        System.out.println(rs1.toString());
        System.out.println(rs2.toString());
    }

}
