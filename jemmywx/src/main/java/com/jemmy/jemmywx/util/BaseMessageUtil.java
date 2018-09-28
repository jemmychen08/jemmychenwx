package com.jemmy.jemmywx.util;

/**
 *
 * 类名称: BaseMessageUtil
 * 类描述: 消息封装工具类的基类,这里采用泛型,方便后期功能扩展
 * @author chenjian
 * 创建时间:
 */
 public interface BaseMessageUtil <T>{
    /**
     * 将回复的信息对象转成xml格式给微信
     * @return
     */
     public abstract  String messageToxml(T t);

    /**
     * 回复的信息封装
     * @param FromUserName
     * @param ToUserName
     * @return
     */
     public abstract  String initMessage(String FromUserName,String ToUserName);
}