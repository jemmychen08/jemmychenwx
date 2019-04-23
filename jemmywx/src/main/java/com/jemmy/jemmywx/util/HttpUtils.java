/**
 * 
 */
package com.jemmy.jemmywx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

/**
 * @Author: JemmyChen
 * 类名称:HttpUtils
 * 类描述:发送http请求的工具类
 * 创建时间:2018/6/14
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static final String DEFAULT_ENCODING = "utf8";
	public static final String JSON_CONTENT_TYPE = "application/json;chatset=utf8";

	public static <T> T postJson(String url, ResponseHandler<T> handler) {
		return postJson(url, null, handler);
	}

	public static <T> T postJson(String url, String jsonString,
			ResponseHandler<T> handler) {
		return postJson(url, jsonString, handler);
	}

	public static <T> T postJson(String url, ResponseHandler<T> handler,
			Header... header) {
		return postJson(url, null, handler, header);
	}

	public static <T> T postJson(String url, String jsonString,
			ResponseHandler<T> handler, Header... header) {
		T res = null;

		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createSSLClientDefault();
		} else if (url.startsWith("http")) {
			client = HttpClients.createDefault();
		} else {
			return res;
		}

		HttpPost post = new HttpPost(url);
		try {
			if (!"".equals(jsonString)&&!"null".equals(jsonString)) {
				StringEntity s = new StringEntity(jsonString, DEFAULT_ENCODING);
				s.setContentType(JSON_CONTENT_TYPE);
				if (header != null) {
					for (Header each : header) {
						post.setHeader(each);
					}
				}
				post.setEntity(s);
			}

			res = client.execute(post, handler);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			post.releaseConnection();
		}
		return res;
	}

	private static HttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						@Override
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
/** 
  * 执行一个HTTP POST请求
  * 
  * @param url         请求的URL地址
  * @return 返回String  
 * @throws UnsupportedEncodingException 
  */ 
	public static String sendPost(String url,TreeMap map) throws UnsupportedEncodingException {
		HttpResponse res = null;
		String strResult = null;
		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createSSLClientDefault();
		} else if (url.startsWith("http")) {
			client = HttpClients.createDefault();
		} else {
			return strResult;
		}
		//设置超时时间
//		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,Constant.HTTPPOSTCONNECTION_TIMEOUT);
		//连接时间20s
//		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,  Constant.HTTPPOSTTIMEOUT);
		//数据传输时间60s
		//建立HttpPost对象 
		HttpPost post = new HttpPost(url);
		//建立一个NameValuePair数组，用于存储欲传送的参数
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		// 循环添加对象
		Entry entry = null;
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			entry = (Entry) iterator.next();
			params.add(new BasicNameValuePair(entry.getKey().toString(),(null == entry.getValue() ? "" : entry.getValue().toString()
					.toString())));
		}
		//设置编码格式。
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(uefEntity);
        logger.debug("executing request:{} " , post.getURI());
		try {
			res = client.execute(post);
			strResult = EntityUtils.toString(res.getEntity(),"UTF-8"); 
			if (strResult != null) {
                logger.debug("Response content:{} " ,strResult);
            }
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			post.releaseConnection();
		}
		return strResult;
	}
	
	
	public static String sendPost(String url) {
		HttpResponse res = null;
		String strResult = null;

		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createSSLClientDefault();
		} else if (url.startsWith("http")) {
			client = HttpClients.createDefault();
		} else {
			return strResult;
		}

		HttpPost post = new HttpPost(url);
		try {
			res = client.execute(post);
			strResult = EntityUtils.toString(res.getEntity()); 
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			post.releaseConnection();
		}
		return strResult;
	}

	public static void main(String[] args) {
		/*try {
			String result = sendPost("http://192.168.30.77:8081/wdptp/reapalcontractnotify.do",new TreeMap());
			System.out.println("result:"+result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int a =2;
		int b =0;
        if(a>0){
            b= 1;
        }else if (a>1){
            b= 2;
        }
        System.out.println(b);
	}
}
