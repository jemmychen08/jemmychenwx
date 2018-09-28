package com.jemmy.jemmywx.controller;

import com.jemmy.jemmywx.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Create by JemmyChen on 2018/4/13
 */
@Controller
public class JemmywxController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value="/weixin.do",method= RequestMethod.GET)
    public  void linkToWX(HttpServletRequest request, HttpServletResponse response){
        String signature =request.getParameter("signature");
        String timestamp =request.getParameter("timestamp");
        String nonce =request.getParameter("nonce");
        String echostr =request.getParameter("echostr");
        logger.debug("linkToWX  signature:{} ,timestamp:{} ,nonce:{} ,echostr:{}",signature,timestamp,nonce,echostr);
        boolean flage=CheckUtil.checkSignature(signature,timestamp,nonce);
        if (flage){
            try {
                response.getWriter().write(echostr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping("/hello.do")
    @ResponseBody
    public String hello(HttpServletRequest request, HttpServletResponse response){
        return "hello,大帅比！";
    }

    @RequestMapping(value = "/weixin.do",method= RequestMethod.POST)
    public void dopost(HttpServletRequest request,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String,String> map = MessageUtil.xmlToMap(request);

        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        String Event = map.get("Event");
        logger.info("--dopost--ToUserName:{} ,FromUserName:{} ,MsgType:{} ,Event:{}",ToUserName,FromUserName,MsgType,Content,Event);
        TextMessageUtil textMessage = new TextMessageUtil();
        String message = null;
        try {
            //处理用户关注事件
            if(MessageUtil.MESSAGE_TYPE_EVENT_SUBSCRIBE.equals(Event)){
                message= textMessage.initMessage(FromUserName,ToUserName);
            }
            //处理文本类型,回复用户输入的内容
            else if(MessageUtil.MESSAGE_TYPE_TEXT.equals(MsgType)){
                if (Content.contains("天气")){
                    //查询天气
                    Content=Content.substring(0,Content.length()-2);
                   String city= java.net.URLEncoder.encode(Content, "utf-8");
                  if(!CityCodeUtil.getCityMap().containsKey(URLDecoder.decode(city))){
                      //如果城市名称输入错误，提示
                      message = textMessage.initMessage(FromUserName, ToUserName,"请输入正确的城市名称如:光山天气");
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
                              count = count + "\n" + dateDay + " " + weather + " " + wind + " " + low+"--"+high;
                          }
                      }
                      message = textMessage.initMessage(FromUserName, ToUserName,count);
                  }
                }else {
                    if("1".equals(Content)){
                        message = textMessage.initMessage(FromUserName, ToUserName);
                    }else {
                        message = textMessage.initMessage(FromUserName, ToUserName,"您的留言我已收到！您可回复数字'1'查看公众号菜单。");
                    }

                }

            }else{
                message = textMessage.initMessage(FromUserName, ToUserName,"暂时支持文字消息");
            }
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out !=null){
                out.close();
            }
        }

    }
}
