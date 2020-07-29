package com.ngyb.utils;

import com.ngyb.utils.constant.Constant;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/29 15:39
 */
public class EmailUtils extends RegularUtils {
    /**
     * @param str
     * @return 是否符合邮箱格式
     */
    public static boolean isEmail(String str) {
        return isMatch(Constant.REGEX_EMAIL, str);
    }
}
