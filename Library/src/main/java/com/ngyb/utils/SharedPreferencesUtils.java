package com.ngyb.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.invoke.ConstantCallSite;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/31 17:57
 */
public class SharedPreferencesUtils {
    private static SharedPreferences sp = null;

    /**
     * @param key   键
     * @param value 值
     */
    public static void setBoolean(Context ctx, String key, boolean value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("Ngyb.xml", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * @param key      键
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("Ngyb.xml", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static void setInt(Context ctx, String key, int value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("Ngyb.xml", Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context ctx, String key, int defValue) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("Ngyb.xml", Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }
}
