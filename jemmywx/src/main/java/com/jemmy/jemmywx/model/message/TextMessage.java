package com.jemmy.jemmywx.model.message;

import com.jemmy.jemmywx.model.message.BaseMessage;

/**
 * 文本消息实体
 * Create by JemmyChen on 2018/4/13
 */

public class TextMessage extends BaseMessage {

    //文本消息内容
    private String Content;


    public TextMessage(){
        super();
    }
    public TextMessage(String content) {
        super();
        Content = content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}

