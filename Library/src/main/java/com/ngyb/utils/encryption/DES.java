package com.ngyb.utils.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
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
 * 日期：2020/5/4 08:41
 */
public class DES {

    /**
     * @param transformation DES/CBC/PKCS5Padding
     * @param algorithm      DES
     * @param key
     * @return
     */
    public static String desEncrypt(String transformation, String algorithm, String key, String str) {
        try {
            if (key == null || key.equals("") || key.length() != 8) {
                return null;
            }
            Cipher cipher = Cipher.getInstance(transformation);
            Key keyPwd = new SecretKeySpec(key.getBytes(), algorithm);
            IvParameterSpec ivPs = new IvParameterSpec(key.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyPwd, ivPs);
            byte[] bytes = cipher.doFinal(str.getBytes());
            return Base64.encode(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param transformation DES/CBC/PKCS5Padding
     * @param algorithm      DES
     * @param key
     * @param str
     * @return
     */
    public static String desDecrypt(String transformation, String algorithm, String key, String str) {
        try {
            if (key == null || key.equals("") || key.length() != 8) {
                return null;
            }
            Cipher cipher = Cipher.getInstance(transformation);
            Key keyPwd = new SecretKeySpec(key.getBytes(), algorithm);
            IvParameterSpec ivPs = new IvParameterSpec(key.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyPwd, ivPs);
            byte[] bytes = cipher.doFinal(Base64.decode(str));
            return new String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String s = desEncrypt("DES/CBC/PKCS5Padding", "DES", "12345678", "ABC");
        System.out.println(s);
        String result = desDecrypt("DES/CBC/PKCS5Padding", "DES", "12345678", s);
        System.out.println(result);
    }
}
