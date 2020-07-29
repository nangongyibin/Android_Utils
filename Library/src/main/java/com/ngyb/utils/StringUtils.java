package com.ngyb.utils;

import com.ngyb.utils.constant.Constant;

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
}
