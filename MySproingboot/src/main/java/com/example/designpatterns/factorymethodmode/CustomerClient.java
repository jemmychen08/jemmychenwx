package com.example.designpatterns.factorymethodmode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 16:29
 * @Description:
 */
public class CustomerClient {
    public static void main(String[] args) {
        LeiFeng leiFeng;
        //大学生学雷锋
        LeiFengFactory fengFactory = new UndergraduateFactory();
        leiFeng= fengFactory.creatLeifeng();
        //志愿者学雷锋
        LeiFengFactory fengFactory1 = new VolunteerFactory();
        leiFeng= fengFactory.creatLeifeng();
    }
}

