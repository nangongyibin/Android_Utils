package com.ngyb.utils;

import android.util.Log;

import com.google.gson.Gson;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/15 09:27
 */
public class LogUtils {
    private static final boolean isPrint = true;

    public static void doLog(String tag, String result) {
        if (isPrint) {
            int NUM = 3000;
            if (result == null) {
                return;
            }
            if (result.length() > NUM) {
                result = result.trim();
                int N = result.length();
                int M = N / NUM;
                for (int i = 0; i < M; i++) {
                    String pre = "";
                    if (i == 0) {
                        pre = tag;
                    }
                    String s = result.substring(i * NUM, i * NUM + NUM);
                    Log.e(tag + "===" + s.length() + ":", s);
                }
                if (N % NUM != 0) {
                    String s = result.substring(M * NUM, N);
                    Log.e(tag + "===" + s.length() + ":", s);
                }
            } else {
                Log.e(tag + ":", "->" + result + "<-");
            }
        }
    }

    public static void doLog(String tag, Object result) {
        doLog(tag, new Gson().toJson(result));
    }
}
