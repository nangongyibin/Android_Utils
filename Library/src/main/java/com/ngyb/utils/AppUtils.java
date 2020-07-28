package com.ngyb.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.jaredrummler.android.processes.models.AndroidProcess;
import com.jaredrummler.android.processes.models.Statm;
import com.ngyb.utils.bean.AppInfoBean;
import com.ngyb.utils.bean.ProcessBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：App应用的工具类
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/1 10:31
 */
public class AppUtils {

    /**
     * 获取App名称
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param ctx
     * @return 项目build中的versionName
     */
    public static String getVersionName(Context ctx) {
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
    public static int getVersionCode(Context ctx) {
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
    public static List<AppInfoBean> getAppInfoList(Context ctx) {
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

    /**
     * @param context     上下文
     * @param packagename 包名
     * @return APPname
     */
    public static String getAppName(Context context, String packagename) {
        String name = "未知应用";
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packagename, 0);
            name = applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            name = "未知应用";
            e.printStackTrace();
        }
        return name;
    }

    /**
     * @param context     上下文
     * @param packagename 包名
     * @return 应用图标
     */
    public static Drawable getAppDrawable(Context context, String packagename) {
        Drawable drawable = null;
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packagename, 0);
            drawable = applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            drawable = context.getResources().getDrawable(R.drawable.ic_launcher);
        }
        return drawable;
    }

    /**
     * @param context 上下文
     * @return 返回已安装的应用的额信息, 未对信息进行重新封装
     */
    public static List<PackageInfo> getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
        return installedPackages;
    }

    /**
     * @param ctx 上下文
     * @return 对于6.0以上的系统进行改进
     */
    public static int getRunningProcess2(Context ctx) {
        List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();
        return processes.size();
    }

    /**
     * @param ctx 上下文
     * @return 手机中可以有的进程的总数
     */
    public static int getAllProcess(Context ctx) {
        PackageManager pm = ctx.getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES | PackageManager.GET_PROVIDERS | PackageManager.GET_SERVICES | PackageManager.GET_RECEIVERS);
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            String processName = packageInfo.applicationInfo.processName;
            hashSet.add(processName);
            ActivityInfo[] activities = packageInfo.activities;
            if (activities != null && activities.length > 0) {
                for (int i1 = 0; i1 < activities.length; i1++) {
                    ActivityInfo activity = activities[i1];
                    hashSet.add(activity.processName);
                }
            }
            ServiceInfo[] services = packageInfo.services;
            if (services != null && services.length > 0) {
                for (int i1 = 0; i1 < services.length; i1++) {
                    ServiceInfo service = services[i1];
                    hashSet.add(service.processName);
                }
            }
            ProviderInfo[] providers = packageInfo.providers;
            if (providers != null && providers.length > 0) {
                for (int i1 = 0; i1 < providers.length; i1++) {
                    ProviderInfo provider = providers[i1];
                    hashSet.add(provider.processName);
                }
            }
            ActivityInfo[] receivers = packageInfo.receivers;
            if (receivers != null && receivers.length > 0) {
                for (int i1 = 0; i1 < receivers.length; i1++) {
                    ActivityInfo receiver = receivers[i1];
                    hashSet.add(receiver.processName);
                }
            }
        }
        return hashSet.size();
    }

    /**
     * @param ctx 上下文
     * @return 获取可用的内存大小
     */
    public static long getAvailMemory(Context ctx) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    /**
     * @param ctx 上下文
     * @return 获取可用的内存大小
     */
    public static long getAllMemory(Context ctx) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return memoryInfo.totalMem;
        } else {
            return getLowTotalMem();
        }
    }

    /**
     * @return 在低版本手机上获取手机总内存的大小
     */
    private static long getLowTotalMem() {
        try {
            File file = new File("proc/meminfo");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLineOne = bufferedReader.readLine();
            String totalMem = strLineOne.replace("MemTotal:", "").replace("kB", "");
            return Long.parseLong(totalMem) * 1024;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param ctx 上下文
     * @return
     */
    public static List<ProcessBean> getAllRunningProcessInfo(Context ctx) {
        ArrayList<ProcessBean> processList = new ArrayList<>();
        PackageManager pm = ctx.getPackageManager();
        List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();
        for (int i = 0; i < processes.size(); i++) {
            AndroidAppProcess androidAppProcess = processes.get(i);
            String processName = androidAppProcess.name;
            List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
            for (int i1 = 0; i1 < installedPackages.size(); i1++) {
                PackageInfo packageInfo = installedPackages.get(i1);
                if (processName.equals(packageInfo.packageName)) {
                    boolean isSys;
                    Drawable drawable = packageInfo.applicationInfo.loadIcon(pm);
                    String name = packageInfo.applicationInfo.loadLabel(pm).toString();
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                        isSys = false;
                    } else {
                        isSys = true;
                    }
                    try {
                        Statm statm = androidAppProcess.statm();
                        long size = statm.getSize();
                        long residentSetSize = statm.getResidentSetSize();
                        ProcessBean processBean = new ProcessBean(drawable, name, processName, (int) residentSetSize, isSys, false);
                        processList.add(processBean);
                    } catch (IOException e) {
                        ProcessBean processBean = new ProcessBean(drawable, name, processName, 0, isSys, false);
                        processList.add(processBean);
                        e.printStackTrace();
                    }
                }
            }
        }
        return processList;
    }

    /**
     * @param ctx 上下文
     *            杀死手机中的进程
     */
    public static void killAllProcess(Context ctx) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        for (int i = 0; i < runningAppProcesses.size(); i++) {
            ActivityManager.RunningAppProcessInfo processInfo = runningAppProcesses.get(i);
            if (processInfo.processName.equals(ctx.getPackageName())) {
                continue;
            }
            am.killBackgroundProcesses(processInfo.processName);
        }
    }

    /**
     * @param ctx         上下文
     * @param packageName 包名
     *                    杀死单个进程
     */
    public static void killProcess(Context ctx, String packageName) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        am.killBackgroundProcesses(packageName);
    }

    /**
     * 获取App信息的一个封装类（包名、版本号、应用信息、图标、名称等）
     *
     * @param ctx 上下文
     * @return
     */
    public static List<ProcessBean> getAppInfos(Context ctx) {
        ArrayList<ProcessBean> processList = new ArrayList<>();
        // 获取应用程序信息
        // 包的管理者
        PackageManager pm = ctx.getPackageManager();
        // 获取系统中安装的所有软件信息
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            // 获取包名
            String packageName = packageInfo.packageName;
            // 获取版本号
            String versionName = packageInfo.versionName;
            // 获取ApplicationInfo
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            int uid = applicationInfo.uid;
            // 获取应用程序的图标
            Drawable icon = applicationInfo.loadIcon(pm);
            // 获取应用程序的名称
            String name = applicationInfo.loadLabel(pm).toString();
            //是否是用户程序
            // 获取应用程序中相关信息，是否是系统程序和是否安装到SK卡
            boolean isUser;
            int flags = applicationInfo.flags;
            if ((applicationInfo.FLAG_SYSTEM & flags) == applicationInfo.FLAG_SYSTEM) {
                //系统程序
                isUser = false;
            } else {
                // 用户程序
                isUser = true;
            }
            // 是否安装到SK卡
            boolean isSD;
            if ((applicationInfo.FLAG_EXTERNAL_STORAGE & flags) == applicationInfo.FLAG_EXTERNAL_STORAGE) {
                // 安装到了sd卡中
                isSD = true;
            } else {
                //安装到手机中
                isSD = false;
            }
            ProcessBean processBean = new ProcessBean(icon, name, packageName, isUser, isSD, versionName);
            processList.add(processBean);
        }
        return processList;
    }

    /**
     * 需添加<uses-permission android:name="android.permission.GET_TASKS
     * 并且必须是系统应用该方法才有效
     * 判断当前APP处于前台还是后台
     *
     * @param context
     * @return
     */
    public static boolean isApplicationBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
