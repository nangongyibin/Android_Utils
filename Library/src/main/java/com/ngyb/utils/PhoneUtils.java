package com.ngyb.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.ngyb.utils.constant.Constant;

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

    /**
     * @param mobileNums
     * @return 验证手机格式
     */
    public static boolean isMobile(String mobileNums) {
        if (TextUtils.isEmpty(mobileNums)) {
            return false;
        } else {
            return mobileNums.matches(Constant.TelRegex);
        }
    }
}
