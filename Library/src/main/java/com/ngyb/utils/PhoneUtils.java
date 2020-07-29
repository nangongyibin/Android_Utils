package com.ngyb.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Xml;

import com.ngyb.utils.constant.Constant;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/18 17:50
 */
public class PhoneUtils extends RegularUtils {

    //获取电话号码
    @SuppressLint("MissingPermission")
    public static String getNativePhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String phone = "N/A";
        phone = tm.getLine1Number();
        return phone;
    }

    /**
     * @param mobileNums
     * @return 验证手机格式 是否符合手机号格式
     */
    public static boolean isMobile(String mobileNums) {
        if (TextUtils.isEmpty(mobileNums)) {
            return false;
        } else {
            return isMatch(Constant.REGEX_MOBILE, mobileNums);
        }
    }

    /**
     * @param telNums
     * @return 是否符合座机号码格式
     */
    public static boolean isTel(String telNums) {
        return isMatch(Constant.REGEX_TEL, telNums);
    }

    /**
     * 判断设备是否是手机
     *
     * @param context
     * @return
     */
    public static boolean isPhone(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    /**
     * 获取当前设备的IMIE，需与上面的isPhone一起使用
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceIMEI(Context context) {
        String deviceId;
        if (isPhone(context)) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        } else {
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return deviceId;
    }

    /**
     * 获取手机状态信息
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getPhoneStatus(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String str = "";
        str += "DeviceId(IMEI) = " + tm.getDeviceId() + "\n";
        str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + "\n";
        str += "Line1Number = " + tm.getLine1Number() + "\n";
        str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
        str += "NetworkOperator = " + tm.getNetworkOperator() + "\n";
        str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
        str += "NetworkType = " + tm.getNetworkType() + "\n";
        str += "PhoneType = " + tm.getPhoneType() + "\n";
        str += "SimCountryIso = " + tm.getSimCountryIso() + "\n";
        str += "SimOperator = " + tm.getSimOperator() + "\n";
        str += "SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
        str += "SimState = " + tm.getSimState() + "\n";
        str += "SubscriberI = " + tm.getSubscriberId() + "\n";
        str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";
        return str;
    }

    /**
     * 是否有SD卡
     *
     * @return
     */
    public static boolean haveSDcard() {
        return android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取mac地址
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAddress = info.getMacAddress();
        if (macAddress == null) {
            return "";
        }
        macAddress = macAddress.replace(":", "");
        return macAddress;
    }

    /**
     * 获取手机厂商 如Xiaomi
     *
     * @return
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取手机型号，如MI2SC
     *
     * @return
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 拨打电话
     *
     * @param context
     * @param phoneNumber
     */
    public static void callPhone(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 获取手机联系人
     *
     * @param context
     * @return
     */
    public static List<HashMap<String, String>> getAllContactInfo(Context context) {
        SystemClock.sleep(3000);
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        //获取内容解析者
        ContentResolver resolver = context.getContentResolver();
        // 获取内容提供者的地址 com.android.contacts
        // raw_contacts表的地址：raw_contacts
        // view_data表的地址： data
        // 生成查询地址
        Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri data_uri = Uri.parse("content://com.android.contacts/data");
        // 查询操作，先查raw_contacts，查询contact_id
        //projecttion：查询的字段
        Cursor cursor = resolver.query(raw_uri, new String[]{"contact_id"}, null, null, null);
        //解析cursor
        while (cursor.moveToNext()) {
            //获取查询的数据
            String contact_id = cursor.getString(0);
            cursor.getColumnIndex("contact_id");
            // cursor.getString(cursor.getColumnIndex("contact_id"));getColumnIndex
            // 查询字段在cursor中的索引值，一半都是用在查询字段比较多的时候
            // 判断contact_id是否为空
            if (!TextUtils.isEmpty(contact_id)) {//null ""
                //根据contact_id查询view_data表中的数据
                // selection 查询条件
                // selectionArgs:查询条件的参数
                // sortOrder 排序
                // 空指针1null方法，2参数为null
                Cursor c = resolver.query(data_uri, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{contact_id}, null);
                HashMap<String, String> map = new HashMap<>();
                //解析c
                while (c.moveToNext()) {
                    //获取数据
                    String data1 = c.getString(0);
                    String mimetype = c.getString(1);
                    // 根据类型去判断获取的data数据并保存
                    if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                        // 电话
                        map.put("phone", data1);
                    } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                        // 姓名
                        map.put("name", data1);
                    }
                }
                // 添加到集合中数据
                list.add(map);
                // 关闭cursor
                c.close();
            }
        }
        // 关闭cursor
        cursor.close();
        return list;
    }
}
