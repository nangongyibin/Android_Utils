package com.ngyb.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/18 17:50
 */
public class PhoneUtils {

    private final TelephonyManager telephonyManager;

    public PhoneUtils(Context context) {
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    //获取电话号码
    public String getNativePhoneNumber() {
        String phone = "N/A";
        phone = telephonyManager.getLine1Number();
        return phone;
    }
}
