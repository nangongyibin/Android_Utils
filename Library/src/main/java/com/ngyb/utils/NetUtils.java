package com.ngyb.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.ngyb.utils.constant.Constant;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/26 10:33
 */
public class NetUtils extends RegularUtils {

    /**
     * 打开网络设置界面
     *
     * @param activity
     * @param requestCode
     */
    public static void openSetting(Activity activity, int requestCode) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 判断是否网络连接
     *
     * @param context
     * @return
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 判断wifi是否连接状态
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 获取移动网络运营商名称，如中国联通、中国移动、中国电信
     *
     * @param context
     * @return
     */
    public static String getNetWorkOperatorName(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperatorName();
    }

    /**
     * 返回移动终端类型
     * PHONE_TYPE_NONE:0 手机制式未知
     * PHONE_TYPE_GSM:1 手机制式为GSM，移动和联通
     * PHONE_TYPE_CDMA:2手机制式为CDMA,电信
     * PHONE_TYPE_SIP:3
     *
     * @param context
     * @return
     */
    public static int getPhoneType(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getPhoneType();
    }

    /**
     * 判断手机连接的网络类型（2G,3G,4G)
     *
     * @param context
     * @return
     */
    public static int getNetWorkClass(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (tm.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return Constant.NETWORK_CLASS_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return Constant.NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return Constant.NETWORK_CLASS_4_G;
            default:
                return Constant.NETWORK_CLASS_UNKNOWN;
        }
    }

    /**
     * 判断当前手机的网络类型（WIFI还是2,3,4G），需要用到上面的方法
     *
     * @param context
     * @return
     */
    public static int getNetworkStatus(Context context) {
        int networkType = Constant.NETWORK_CLASS_UNKNOWN;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (type == ConnectivityManager.TYPE_WIFI) {
                networkType = Constant.NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                networkType = getNetWorkClass(context);
            }
        }
        return networkType;
    }

    /**
     * @param str
     * @return 是否符合网址格式
     */
    public static boolean isURL(String str) {
        return isMatch(Constant.REGEX_URL, str);
    }
}
