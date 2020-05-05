package com.ngyb.utils.encryption;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.interfaces.PBEKey;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/5 17:18
 */
public class SignatureUtils {

    public static String sign(String algorithm, String str, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privateKey);
            signature.update(str.getBytes());
            byte[] sign = signature.sign();
            return Base64.encode(sign);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verify(String algorithm, String str, PublicKey publicKey, String sign) {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(publicKey);
            signature.update(str.getBytes());
            return signature.verify(Base64.decode(sign));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        RSA.generateRSAKey("RSA");


        String sign = sign("SHA256withRSA", "1", RSA.getPrivateKey("RSA", null));
        System.out.println(sign);
        boolean verify = verify("SHA256withRSA", "1", RSA.getPublicKey("RSA", null), sign);
        System.out.println(verify);
    }
}
