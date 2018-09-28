package com.jemmy.jemmywx.util;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.*;

/**
 *
 * 类名称: CheckUtil
 * 类描述: 检验是否是微信消息
 * @author chenjian
 * 创建时间:
 */

public class CheckUtil {

        private static  final  String Token=PropertyUtil.getProperty("token");

        private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        /**
         * 确认请求来自微信服务器
         * @return
         */
        public  static boolean  checkSignature(String signature,String timestamp,String nonce){
            boolean flag=false;
            // 1）将token、timestamp、nonce三个参数进行字典序排序
            String[] params = {Token,timestamp,nonce};
            Arrays.sort(params);
            // 2）将三个参数字符串拼接成一个字符串进行sha1加密
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i <params.length ; i++) {
                sb.append(params[i]);
            }
            String str=sb.toString();
                //进行SHA1加密
                String checkStr=encode(str);
            // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            if (!StringUtils.isEmpty(checkStr) && signature.equals(checkStr)){
                flag=true;
            }
            return flag;
            }


    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
