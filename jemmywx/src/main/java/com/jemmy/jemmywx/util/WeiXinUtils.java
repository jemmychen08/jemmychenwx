package com.jemmy.jemmywx.util;

import com.jemmy.jemmywx.model.AccessTokenModel;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * @Author: JemmyChen
 * 类名称:WeiXinUtils
 * 类描述:
 * 创建时间:2018/6/14
 */

public class WeiXinUtils {
    private static final Logger logger = LoggerFactory.getLogger(WeiXinUtils.class);

    private static String APPID = PropertyUtil.getProperty("appid");
    private static String APPSECRET = PropertyUtil.getProperty("appsecret");
    private static final String ACCESS_TOKEN_URL = PropertyUtil.getProperty("access_token_url");
    /**
     * 处理doget请求
     * @param url
     * @return
     */
    public static JSONObject doGetstr(String url){

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity!=null){
                String result = EntityUtils.toString(entity);
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
    /**
     * 处理post请求
     * @param url
     * @return
     */
    public static JSONObject doPoststr(String url,String outStr){

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "utf-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            jsonObject =JSONObject.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static AccessTokenModel getAccessToken(){

        Jedis jedis  = RedisUtils.getJedis();
        AccessTokenModel token = new AccessTokenModel();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject json = doGetstr(url);
        if(json!=null){
            token.setAccess_token(json.getString("access_token"));
            token.setExpires_in(json.getInt("expires_in"));
            jedis.set("access_token", json.getString("access_token"));
            jedis.expire("access_token", 60*60*2);
            logger.info("从接口中获取access_token,存放在redis缓存中:{} ,设置过期时间为 2 个小时",token.getAccess_token());
        }
        RedisUtils.returnResource(jedis);
        return token;
    }
    /**
     * 获取凭证
     * @return
     */
    public static String  getAccess_Token(){
        Jedis jedis  = RedisUtils.getJedis();
        String access_token = jedis.get("access_token");

        if(access_token==null){
            AccessTokenModel token = getAccessToken();
            access_token = token.getAccess_token();
        }else {
            jedis.expire("access_token",60*60*2);
            logger.info("从缓存中获取access_token:{} ,并重新设置access_token过期时间",access_token);
        }
        RedisUtils.returnResource(jedis);
        return access_token;
    }

    public static void main(String[] args) {
         getAccess_Token();
    }
}
