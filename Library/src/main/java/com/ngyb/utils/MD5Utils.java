package com.ngyb.utils;

import android.os.Message;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/9 17:19
 */
public class MD5Utils {
    /**
     * @param str 要加密的字符串
     * @return md5加密后的字符串
     */
    public static String encode(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] digest = md5.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int a = b & 0xff;
                String hexString = Integer.toHexString(a);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
