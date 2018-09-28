package com.jemmy.jemmywx.model.message;

/**
 * 消息实体基类
 * Create by JemmyChen on 2018/4/13
 */

public class BaseMessage {

    // 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型
    private String MsgType;

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    // 消息id，64位整型
    private long MsgId;

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
