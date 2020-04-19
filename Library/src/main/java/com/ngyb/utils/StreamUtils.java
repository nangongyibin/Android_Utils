package com.ngyb.utils;

import android.os.Build;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/4 21:48
 */
public class StreamUtils {

    /**
     * @param is
     * @return
     */
    public static String StreamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void close(AutoCloseable autoCloseable) throws Exception {
        if (autoCloseable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                autoCloseable.close();
            }
        }
    }

    /**
     * @param is
     * @return
     * @throws IOException
     */
    public static String convertStreamToString(InputStream is) throws IOException {
        try {
            if (is != null) {
                StringBuilder sb = new StringBuilder();
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return "";
    }
}
