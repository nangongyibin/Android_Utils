package com.ngyb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/25 23:10
 */
public class OperatorAppUtils {
    /**
     * 安装指定路径下的apk
     *
     * @param activity
     * @param filePath
     * @param requestCode
     */
    public static void installApk(Activity activity, String filePath, int requestCode) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 卸载指定包名的APP
     *
     * @param activity
     * @param packageName
     * @param requestCode
     */
    public static void uninstallApp(Activity activity, String packageName, int requestCode) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DELETE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("package:" + packageName));
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开指定包名的APP
     *
     * @param context
     * @param packageName
     */
    public static void openOtherApp(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        Intent launchIntentForPackage = pm.getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            context.startActivity(launchIntentForPackage);
        }
    }

    /**
     * 打开指定包名的APP信息界面
     *
     * @param context
     * @param packageName
     */
    public static void showAppInfo(Context context, String packageName) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("packageName:" + packageName));
        context.startActivity(intent);
    }

    /**
     * 分享Apk信息
     *
     * @param context
     * @param info
     */
    public static void shareApkInfo(Context context, String info) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, info);
        context.startActivity(intent);
    }
}
