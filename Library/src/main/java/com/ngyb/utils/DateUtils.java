package com.ngyb.utils;

import java.util.Calendar;

/**
 * 作者：南宫燚滨
 * 描述：日期的工具类
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/14 15:20
 */
public class DateUtils {
    /**
     * @return 获取当前日期的年
     */
    public int getCurYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * @return 获取当前日期的月
     */
    public int getCurMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @return 获取当前日期的日
     */
    public int getCurDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
