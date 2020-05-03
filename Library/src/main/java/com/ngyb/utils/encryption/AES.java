package com.ngyb.utils.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/8/22 14:22
 */
public class AES {

    /**
     * AES加密
     *
     * @param kStr           加密密钥
     * @param pStr           准备加密的字符串
     * @param charSetName    字符串编码格式
     * @param algorithm      密钥算法名称  AES
     * @param transformation 使用的算法 算法/模式/补码方式, 目前支持ECB和CBC模式
     * @param iv             偏移量,CBC模式时需要
     * @return Base64编码后的字符串
     */
    public static String aesEncrypt(String kStr, String pStr, String charSetName, String algorithm, String transformation, String iv) {
        try {
            byte[] key = kStr.getBytes(charSetName);
            //创建AES密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, algorithm);
            //创建密码器
            Cipher cipher = Cipher.getInstance(transformation);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(charSetName));
            //初始化加密器
            if (transformation.contains("CBC")) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            }
            // 加密
            byte[] bStr = cipher.doFinal(pStr.getBytes(charSetName));
            return Base64.encode(bStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * AES解密
     *
     * @param kStr           加密密钥
     * @param pStr           准备解密的字符串
     * @param charSetName    字符串编码格式
     * @param algorithm      密钥算法名称  AES
     * @param transformation 使用的算法 算法/模式/补码方式, 目前支持ECB和CBC模式
     * @param iv             偏移量,CBC模式时需要
     * @return Base64编码后的字符串
     */
    public static String aesDecrypt(String kStr, String pStr, String charSetName, String algorithm, String transformation, String iv) {
        try {
            byte[] key = kStr.getBytes(charSetName);
            //创建AES密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, algorithm);
            //创建密码器
            Cipher cipher = Cipher.getInstance(transformation);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(charSetName));
            //初始化加密器
            if (transformation.contains("CBC")) {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            }
            byte[] decode = Base64.decode(pStr);
            // 加密
            byte[] bStr = cipher.doFinal(decode);
            return new String(bStr, charSetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String s = aesEncrypt("1234567812345678", "ngyb", "UTF-8", "AES", "AES/CBC/PKCS5Padding", "1234123412341234");
        System.out.println(s);
        String s1 = aesDecrypt("1234567812345678", s, "UTF-8", "AES", "AES/CBC/PKCS5Padding", "1234123412341234");
        System.out.println(s1);
    }
}
