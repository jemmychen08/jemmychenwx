package com.jemmy.jemmywx.util;

import com.jemmy.jemmywx.model.message.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 类名称: MessageUtil
 * 类描述: 微信发送过来消息的处理类，处理XML消息
 * @author chenjian
 * 创建时间:
 */
public class MessageUtil {
    // 消息类型：文本
   public static  final  String MESSAGE_TYPE_TEXT = "text";
    // 消息类型：图片
    public static final String MESSAGE_TYPE_IMAGE = "image";
    // 消息类型：语音
    public static final String MESSAGE_TYPE_VOICE = "voice";
    // 消息类型：视频
    public static final String MESSAGE_TYPE_VIDEO = "video";
    // 消息类型：地理位置
    public static final String MESSAGE_TYPE_LOCATION = "location";
    // 消息类型：链接
    public static final String MESSAGE_TYPE_LINK = "link";
    // 消息类型：事件推送
    public static final String MESSAGE_TYPE_EVENT = "event";
    // 消息类型：订阅
    public static  final  String MESSAGE_TYPE_EVENT_SUBSCRIBE = "subscribe";
    // 消息类型：取消订阅
    public static  final  String MESSAGE_TYPE_EVENT_UNSUBSCRIBE = "subscribe";
    // 事件类型：scan(用户已关注时的扫描带参数二维码)
    public static final String EVENT_TYPE_SCAN = "scan";
    // 事件类型：LOCATION(上报地理位置)
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**
     * 将微信的请求中参数转成map
     * @param request
     * @return
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request){

        Map<String,String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();
        InputStream in = null;
        try {
            in = request.getInputStream();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String messageToXml(BaseMessage message,String aa) {
        String backXml = null;
        if (message != null) {
            if (message instanceof TextMessage) {
                backXml = messageToXml((TextMessage)message);
            } else if (message instanceof ImageMessage) {
                backXml = messageToXml((ImageMessage)message);
            } else if (message instanceof VoiceMessage) {
                backXml = messageToXml((VoiceMessage)message);
            } else if (message instanceof VideoMessage) {
                backXml = messageToXml((VideoMessage)message);
            } else if (message instanceof MusicMessage) {
                backXml = messageToXml((MusicMessage)message);
            } else if (message instanceof NewsMessage) {
                backXml = messageToXml((NewsMessage)message);
            }
        }
        return backXml;
    }

    /**
     * 扩展xstream使其支持CDATA
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     * @return xml
     */
    public  static String messageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图片消息对象转换成xml
     *
     * @param imageMessage 图片消息对象
     * @return xml
     */
    public static String messageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 语音消息对象转换成xml
     *
     * @param voiceMessage 语音消息对象
     * @return xml
     */
    public static String messageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成xml
     *
     * @param videoMessage 视频消息对象
     * @return xml
     */
    public static String messageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐消息对象转换成xml
     *
     * @param musicMessage 音乐消息对象
     * @return xml
     */
    public static String messageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String messageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }
}