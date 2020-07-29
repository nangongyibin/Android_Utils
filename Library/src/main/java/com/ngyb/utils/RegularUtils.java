package com.ngyb.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/28 13:56
 */
public class RegularUtils {
    /**
     * 正则表达式匹配
     *
     * @param regex
     * @param input
     * @return
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return !TextUtils.isEmpty(input) && Pattern.matches(regex, input);
    }
}
