package com.ngyb.utils.encryption;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/4 10:42
 */
public class RSA {

    private static String PRIVATEKEY;
    private static String PUBLICKEY;
    public static final int MAX_ENCRYPT_SIZE = 117;
    public static final int MAX_DECRYPT_SIZE = 128;

    public static void generateRSAKey(String algorithm) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
            generator.initialize(1024);
            KeyPair keyPair = generator.generateKeyPair();
            PrivateKey aPrivate = keyPair.getPrivate();
            PublicKey aPublic = keyPair.getPublic();
            PRIVATEKEY = Base64.encode(aPrivate.getEncoded());
            PUBLICKEY = Base64.encode(aPublic.getEncoded());
            System.out.println("私钥："+PRIVATEKEY);
            System.out.println("公钥："+PUBLICKEY);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static PrivateKey getPrivateKey(String algorithm, String privateKey) {
        if (privateKey != null) {
            PRIVATEKEY = privateKey;
        }
        PrivateKey aPrivateKey = null;
//        System.out.println("私钥："+PRIVATEKEY);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            aPrivateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(PRIVATEKEY)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return aPrivateKey;
    }

    public static PublicKey getPublicKey(String algorithm, String publicKey) {
        if (publicKey != null) {
            PUBLICKEY = publicKey;
        }
//        System.out.println("公钥："+PUBLICKEY);
        PublicKey aPublicKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            aPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decode(PUBLICKEY)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return aPublicKey;
    }

    /**
     * @param transformation
     * @param str
     * @param privateKey
     * @return 私钥加密
     */
    public static String rsaEncryptByPrivateKey(String transformation, String str, PrivateKey privateKey) {
        try {
            byte[] bytes = str.getBytes();
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            int offset = 0; //偏移量
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (bytes.length - offset > 0) {
                if (bytes.length - offset >= MAX_ENCRYPT_SIZE) {
                    buffer = cipher.doFinal(bytes, offset, MAX_ENCRYPT_SIZE);
                    offset += MAX_ENCRYPT_SIZE;
                } else {
                    buffer = cipher.doFinal(bytes, offset, bytes.length - offset);
                    offset = bytes.length;
                }
                baos.write(buffer,0,buffer.length);
            }
            return Base64.encode(baos.toByteArray());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
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
     * @param transformation
     * @param str
     * @param publicKey
     * @return
     */
    public static String rsaDecryptByPublicKey(String transformation, String str, PublicKey publicKey) {
        try {
            byte[] bytes = Base64.decode(str);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            int offset = 0; //偏移量
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (bytes.length - offset > 0) {
                if (bytes.length - offset >= MAX_DECRYPT_SIZE) {
                    buffer = cipher.doFinal(bytes, offset, MAX_DECRYPT_SIZE);
                    offset += MAX_DECRYPT_SIZE;
                } else {
                    buffer = cipher.doFinal(bytes, offset, bytes.length - offset);
                    offset = bytes.length;
                }
                baos.write(buffer,0,buffer.length);
            }
            return baos.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param transformation
     * @param str
     * @param publicKey
     * @return 公钥加密
     */
    public static String rsaEncryptByPublicKey(String transformation, String str, PublicKey publicKey) {
        try {
            byte[] bytes = str.getBytes();
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            int offset = 0; //偏移量
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (bytes.length - offset > 0) {
                if (bytes.length - offset >= MAX_ENCRYPT_SIZE) {
                    buffer = cipher.doFinal(bytes, offset, MAX_ENCRYPT_SIZE);
                    offset += MAX_ENCRYPT_SIZE;
                } else {
                    buffer = cipher.doFinal(bytes, offset, bytes.length - offset);
                    offset = bytes.length;
                }
                baos.write(buffer);
            }
            return Base64.encode(baos.toByteArray());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String rsaDecryptByPrivateKey(String transformation, String str, PrivateKey privateKey) {
        try {
            byte[] bytes = Base64.decode(str);
//            System.out.println(bytes.length);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int offset = 0; //偏移量
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (bytes.length - offset > 0) {
                if (bytes.length - offset >= MAX_DECRYPT_SIZE) {
                    buffer = cipher.doFinal(bytes, offset, MAX_DECRYPT_SIZE);
                    offset += MAX_DECRYPT_SIZE;
                } else {
                    buffer = cipher.doFinal(bytes, offset, bytes.length - offset);
                    offset = bytes.length;
                }
                baos.write(buffer);
            }
            return baos.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        try {
//            String encode = Base64.encode("a".getBytes());
//            System.out.println("加密："+encode);
//            byte[] decode = Base64.decode(encode);
//            System.out.println("解密："+new String(decode));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        generateRSAKey("RSA");

//        String rsa = rsaEncryptByPrivateKey("RSA", "1", getPrivateKey("RSA", null));
//        System.out.println("私钥加密：" + rsa);
//        String ori = rsaDecryptByPublicKey("RSA", rsa, getPublicKey("RSA", null));
//        System.out.println("公钥解密："+ori);

        String rsa = rsaEncryptByPublicKey("RSA", "1", getPublicKey("RSA", null));
        System.out.println("公钥加密：" + rsa);
        String ori = rsaDecryptByPrivateKey("RSA", rsa, getPrivateKey("RSA", null));
        System.out.println("私钥解密："+ori);
    }
}
