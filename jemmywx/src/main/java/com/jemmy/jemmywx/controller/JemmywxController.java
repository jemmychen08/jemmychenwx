package com.jemmy.jemmywx.controller;

import com.jemmy.jemmywx.model.message.BaseMessage;
import com.jemmy.jemmywx.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        PrintWriter out = null;
        String message = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            //将微信请求xml转为map格式，获取所需的参数
            Map<String,String> map = MessageUtil.xmlToMap(request);
            BaseMessage baseMessage = BaseMessageUtils.getRespMessage(map);
            if (baseMessage != null){
                // 将消息对象转换成xml
                message =MessageUtil.messageToXml(baseMessage,"a");
            }
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if( out != null){
                out.close();
            }
        }

    }
}
