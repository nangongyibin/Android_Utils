package com.ngyb.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.ngyb.utils.constant.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/29 15:41
 */
public class StringUtils extends RegularUtils {

    /**
     * @param str
     * @return 是否符合汉字
     */
    public static boolean isChz(String str) {
        return isMatch(Constant.REGEX_CHZ, str);
    }

    /**
     * @param str
     * @return 是否符合用户名
     */
    public static boolean isUserName(String str) {
        return isMatch(Constant.REGEX_USERNAME, str);
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断输入的字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return !isNotNull(str);
    }

    /**
     * @param str    空字符
     * @param custom 为空要转化的字符串
     * @return
     */
    public static String NullToCustom(String str, String custom) {
        if (isNull(custom)) {
            if (isNull(str)) {
                return "";
            }
        } else {
            if (isNull(str)) {
                return custom;
            }
        }
        return str;
    }

    /**
     * 如果是空转化为""
     *
     * @param str
     * @return
     */
    public static String NullTo(String str) {
        return NullToCustom(str, "");
    }

    public static String getString(Context context, String filename) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
