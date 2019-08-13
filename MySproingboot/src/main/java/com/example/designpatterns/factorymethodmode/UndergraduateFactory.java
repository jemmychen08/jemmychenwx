package com.example.designpatterns.factorymethodmode;

/**
 * @Auther: jemmy
 * @Date: 2019/6/25 16:27
 * @Description:
 */
public class UndergraduateFactory extends LeiFengFactory{
      @Override
      public   LeiFeng creatLeifeng() {
            return new Udergraduate();
        }
}
