package com.ngyb.utils.encryption;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/3 18:12
 */
public class Kaiser {
    public static String kaiserEncrypt(int key, String source) {
        if (source == null || source.equals("")) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] array = source.toCharArray();
        for (char c : array) {
            int value = c;
            value += key;
            char result = (char) value;
            sb.append(result);
        }
        return sb.toString();
    }

    public static String kaiserDecryption(int key,String source){
        if (source == null || source.equals("")) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] array = source.toCharArray();
        for (char c : array) {
            int value = c;
            value -= key;
            char result = (char) value;
            sb.append(result);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String bcdef = kaiserDecryption(1, "cdefg");
        System.out.println(bcdef);
    }
}
