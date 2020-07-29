package com.ngyb.utils;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/4 17:48
 */
public class HexUtils {
    public static final char[] HEX = "01234567890abcdef".toCharArray();

    public static String toHex(byte[] str) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : str) {
            int value = aByte & 0xff;
            String s = Integer.toHexString(value);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s);
        }
        return sb.toString();
    }

    public static String bytesToHex(byte[] buf){
        char[] chs = new char[buf.length *2];
        for (int i = 0,offset = 0; i < buf.length; i++) {
            chs[offset++] = HEX[buf[i]>>4&0xf];
            chs[offset++] = HEX[buf[i]&0xf];
        }
        return new String(chs);
    }
}
