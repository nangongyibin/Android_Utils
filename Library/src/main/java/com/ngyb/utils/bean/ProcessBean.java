package com.ngyb.utils.bean;

import android.graphics.drawable.Drawable;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/10 12:28
 */
public class ProcessBean {
    //图片
    private Drawable drawable;
    //名称
    private String name;
    //应用程序名
    private String packageName;
    //大小
    private int size;
    //用于判断是否是系统进程
    private boolean isSys;
    //bean指向的条目有没有选中
    private boolean isCheck;
    //是否安装到SK卡
    private boolean isSD;
    //版本号
    private String versionName;

    public ProcessBean(Drawable drawable, String name, String packageName, int size, boolean isSys, boolean isCheck) {
        this.drawable = drawable;
        this.name = name;
        this.packageName = packageName;
        this.size = size;
        this.isSys = isSys;
        this.isCheck = isCheck;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isSys() {
        return isSys;
    }

    public void setSys(boolean sys) {
        isSys = sys;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isSD() {
        return isSD;
    }

    public void setSD(boolean SD) {
        isSD = SD;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public ProcessBean(Drawable drawable, String name, String packageName, boolean isSys, boolean isSD, String versionName) {
        this.drawable = drawable;
        this.name = name;
        this.packageName = packageName;
        this.isSys = isSys;
        this.isSD = isSD;
        this.versionName = versionName;
    }
}
