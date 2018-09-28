package com.jemmy.jemmywx.model;

/**
 * 消息实体基类
 * Create by JemmyChen on 2018/4/13
 */

public class BaseMessage {

    protected String ToUserName;
    protected String FromUserName;
    protected long CreateTime;
    protected String MsgType;

    public BaseMessage() {
        super();
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
