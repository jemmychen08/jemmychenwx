package com.example.designpatterns.factorymethodmode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 16:29
 * @Description:
 */
public class VolunteerFactory extends LeiFengFactory {
    @Override
    LeiFeng creatLeifeng() {
        return new Volunteer();
    }
}
