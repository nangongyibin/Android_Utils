package com.ngyb.utils.bean;

import android.graphics.drawable.Drawable;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/5 17:56
 */
public class AppInfoBean {
    private Drawable drawable;
    private String name;
    private String packageName;

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

    public AppInfoBean(Drawable drawable, String name, String packageName) {
        this.drawable = drawable;
        this.name = name;
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "AppInfoBean{" +
                "drawable=" + drawable +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
