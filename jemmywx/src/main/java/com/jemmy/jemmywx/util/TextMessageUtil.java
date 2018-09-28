package com.jemmy.jemmywx.util;

import com.jemmy.jemmywx.model.TextMessage;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * 类名称: TextMessageUtil
 * 类描述: 文本消息回复工具类
 * @author chenjian
 * 创建时间:
 */
public class TextMessageUtil implements BaseMessageUtil<TextMessage> {


    @Override
    public  String messageToxml(TextMessage textMessage) {
        XStream xstream  = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    //关注事件
    @Override
    public String initMessage(String FromUserName, String ToUserName) {
        StringBuffer sb =new StringBuffer();
        TextMessage text = new TextMessage();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        sb.append("欢迎关注心灵栖息ol \n");
        sb.append("回复如：北京天气 可查询天气情况 \n");
        sb.append("回复如：查询小姐姐 此功能暂未开发，敬请期待！\n");
        sb.append("您也可以给我留言，说说你的心里话。树洞！\n");
        sb.append("回复数字1调出此菜单");
        text.setContent(sb.toString());
        text.setCreateTime(new Date().getTime());
        text.setMsgType(MessageUtil.MESSAGE_TYPE_TEXT);
        return  messageToxml(text);
    }

    //添加封装发送消息的方法，重载，将内容传入
    public String initMessage(String FromUserName, String ToUserName,String Content) {
        TextMessage text = new TextMessage();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent(Content);
        text.setCreateTime(new Date().getTime());
        text.setMsgType(MessageUtil.MESSAGE_TYPE_TEXT);
        return  messageToxml(text);
    }
}