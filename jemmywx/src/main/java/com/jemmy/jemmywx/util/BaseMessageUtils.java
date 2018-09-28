package com.jemmy.jemmywx.util;

import com.jemmy.jemmywx.model.message.BaseMessage;
import com.jemmy.jemmywx.model.message.TextMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * @Author: JemmyChen
 * 类名称:
 * 类描述:
 * 创建时间:2018/9/28
 */

public class BaseMessageUtils {

    private static Logger logger = LoggerFactory.getLogger(BaseMessageUtils.class);

    // 返回的xml
    public String respXML = null;

    // 默认返回的文本消息内容
    public static String respContent = null;
    // 发送方帐号
    public static String fromUserName = null;
    // 开发者微信号
    public static String toUserName = null;
    // 消息类型
    public static String msgType = null;
    // 消息内容
    public static String message = null;
    // 消息id
    public static String MsgId = null;
    // 发送时间 integer
    public static String CreateTime = null;
    // 事件类型
    public static String eventType = null;

    // 自定义菜单key
    public String eventKey = null;

    /**
     * 初始化各种类型公共字段
     *
     * @param requestMap
     */
    private static void init(Map<String, String> requestMap) {
        // 发送方帐号
        fromUserName = requestMap.get("FromUserName");
        // 开发者微信号
        toUserName = requestMap.get("ToUserName");
        // 消息类型
        msgType = requestMap.get("MsgType");
        // 消息内容
        message = requestMap.get("Content");
        // 消息id
        MsgId = requestMap.get("MsgId");
        // 发送时间 integer
        CreateTime = requestMap.get("CreateTime");
        // 事件类型
        eventType = requestMap.get("Event");
        logger.info("--dopost--ToUserName:{} ,FromUserName:{} ,MsgType:{} ,Event:{}",toUserName,fromUserName,msgType,message,eventType);
    }

    public static BaseMessage getRespMessage(Map<String, String> requestMap) {

        BaseMessage baseMessage = null;
        try {
            init(requestMap);
            // 文本消息
            if (msgType.equals(MessageUtil.MESSAGE_TYPE_TEXT)) {
                baseMessage = getTextRespose();
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.MESSAGE_TYPE_IMAGE)) {
                baseMessage = getImageRespose();
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.MESSAGE_TYPE_VOICE)) {
                baseMessage = getVoiceRespose();
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.MESSAGE_TYPE_VIDEO)) {
                baseMessage = getVideoRespose();
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.MESSAGE_TYPE_LOCATION)) {
                baseMessage = getLocationRespose();
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.MESSAGE_TYPE_LINK)) {
                baseMessage = getLinkRespose();
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.MESSAGE_TYPE_EVENT)) {
                // 事件类型
                eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.MESSAGE_TYPE_EVENT_SUBSCRIBE)) {
                    baseMessage = getEvent_SubscribeRespose();
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.MESSAGE_TYPE_EVENT_UNSUBSCRIBE)) {
                    // 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                    getEvent_UnsubscribeRespose();
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // 处理扫描带参数二维码事件
                    baseMessage = getEvent_ScanRespose();
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // 处理上报地理位置事件
                    baseMessage = getEvent_LocationRespose();

                }
            } else {
                baseMessage = getNoRespose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseMessage;
    }

    /**
     * 关注帐号事件
     *
     * @return
     */
    protected static TextMessage getEvent_SubscribeRespose() {
        StringBuffer sb =new StringBuffer();
        sb.append("欢迎关注心灵栖息ol \n");
        sb.append("回复如：北京天气 可查询天气情况 \n");
        sb.append("回复如：查询小姐姐 此功能暂未开发，敬请期待！\n");
        sb.append("您也可以给我留言，说说你的心里话！\n");
        sb.append("回复数字1调出此菜单");
        respContent = sb.toString();

        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.MESSAGE_TYPE_TEXT);
        textMessage.setContent(respContent);

        return textMessage;
    }

    /**
     * 接收文本消息
     * @return
     */
    protected static TextMessage getTextRespose() {
        TextMessage textMessage = new TextMessage();
            try {
                //处理文本类型,回复用户输入的内容
                if (message.contains("天气")){
                    //查询天气
                    message=message.substring(0,message.length()-2);
                    String city= java.net.URLEncoder.encode(message, "utf-8");
                    if(!CityCodeUtil.getCityMap().containsKey(URLDecoder.decode(city))){
                        //如果城市名称输入错误，提示
                        respContent = "\"请输入正确的城市名称如:光山天气\"";
                        textMessage.setContent(respContent);
                    }else {
                        //存在城市，查询天气
                        String sb=  WeatherUtil.getWeatherInfo("http://wthrcdn.etouch.cn/weather_mini?city="+city);
                        String count = "";
                        JSONObject jsonObject = JSONObject.fromObject(sb);
                        JSONObject data = (JSONObject) jsonObject.get("data");
                        JSONArray jsonArray =data. getJSONArray("forecast");
                        if (jsonArray.size() > 0) {
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String dateDay = jsonObject1.optString("date");
                                String weather = jsonObject1.optString("type");
                                String wind = jsonObject1.optString("fengxiang");
                                String high = jsonObject1.optString("high");
                                String low = jsonObject1.optString("low");
                                respContent = respContent + "\n" + dateDay + " " + weather + " " + wind + " " + low+"--"+high;
                            }
                        }
                    }
                }else {
                    if("1".equals(message)){
                        StringBuffer sb =new StringBuffer();
                        sb.append("欢迎关注心灵栖息ol \n");
                        sb.append("回复如：北京天气 可查询天气情况 \n");
                        sb.append("回复如：查询小姐姐 此功能暂未开发，敬请期待！\n");
                        sb.append("您也可以给我留言，说说你的心里话。树洞！\n");
                        sb.append("回复数字1调出此菜单");
                        respContent = sb.toString();
                    }else {
                        respContent = "您的留言我已收到！您可回复数字'1'查看公众号菜单。";
                    }
                    textMessage.setToUserName(fromUserName);
                    textMessage.setFromUserName(toUserName);
                    textMessage.setContent(respContent);
                }
            }catch (Exception e){

            }
        return textMessage;
    }

    /**
     * 接收图片消息
     * @return
     */
    protected static BaseMessage getImageRespose() {
        return null;
    }

    /**
     * 接收地理位置事件
     * @return
     */
    protected static BaseMessage getEvent_LocationRespose()  {
        return null;
    }

    /**
     * 二维码扫描事件
     * @return
     */
    protected static BaseMessage getEvent_ScanRespose()  {
        return null;
    }

    /**
     * 接收链接消息
     * @return
     */
    protected static BaseMessage getLinkRespose() {
        return null;
    }

    /**
     * 接收地理位置消息
     * @return
     */
    protected static BaseMessage getLocationRespose() {
        return null;
    }

    /**
     * 接收视频消息
     * @return
     */
    protected static BaseMessage getVideoRespose() {
        return null;
    }

    /**
     * 接收语音消息
     * @return
     */
    protected static BaseMessage getVoiceRespose() {
        return null;
    }

    /**
     * 处理未知消息类型
     * @return
     */
    private static TextMessage getNoRespose() {
        respContent = "未知的消息类型！";
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.MESSAGE_TYPE_TEXT);
        textMessage.setContent(respContent);
        return textMessage;
    }


    /**
     * 取消关注事件
     */
    protected static void getEvent_UnsubscribeRespose() {
    }

}
