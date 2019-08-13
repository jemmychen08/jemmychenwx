package com.example.designpatterns.template;

/**
 * @Auther: jemmy
 * @Date: 2019/6/27 15:55
 * @Description:
 */
public  abstract  class CookeTemplate {

    protected void process(){
        prepar();
        cooke();
        carry();
    }
   abstract void prepar();
   abstract  void cooke();
   abstract  void carry();
}
