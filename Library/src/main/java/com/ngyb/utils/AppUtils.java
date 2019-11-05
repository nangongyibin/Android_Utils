package com.ngyb.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.ngyb.utils.bean.AppInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：App应用的工具类
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/1 10:31
 */
public class AppUtils {

    /**
     * @param ctx
     * @return 项目build中的versionName
     */
    public String getVersionName(Context ctx) {
        try {
            PackageManager packageManager = ctx.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0.1";
    }

    /**
     * @param ctx
     * @return 项目build中的versionCode
     */
    public int getVersionCode(Context ctx) {
        try {
            PackageManager packageManager = ctx.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * @param ctx 上下文
     * @return 获取的应用信息，包含三部分，图片 名称 包名 返回的数据是以集合的形式提供
     */
    public List<AppInfoBean> getAppInfoList(Context ctx) {
        List<AppInfoBean> appInfoBeanList = new ArrayList<>();
        PackageManager pm = ctx.getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            String packageName = packageInfo.packageName;
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            Drawable drawable = applicationInfo.loadIcon(pm);
            String name = applicationInfo.loadLabel(pm).toString();
            AppInfoBean appInfoBean = new AppInfoBean(drawable, name, packageName);
            appInfoBeanList.add(appInfoBean);
        }
        return appInfoBeanList;
    }
}
