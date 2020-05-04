package com.ngyb.utils.encryption;

import com.ngyb.utils.Hex;
import com.ngyb.utils.MD5Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/4 17:36
 */
public class MD5 {
    public static String Md5Str(String algorithm, String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] bytes = digest.digest(str.getBytes());
            String s = Hex.toHex(bytes);
            return s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String Md5File(String algorithm, String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                digest.update(buf, 0, len);
            }
            byte[] bytes = digest.digest();
            String s = Hex.toHex(bytes);
            return s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String md5 = Md5Str("MD5", "1");
        System.out.println(md5);
        MD5Utils md5Utils = new MD5Utils();
        System.out.println(md5Utils.encode("1"));
    }
}
