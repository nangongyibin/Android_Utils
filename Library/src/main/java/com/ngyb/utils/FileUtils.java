package com.ngyb.utils;

import android.util.Log;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/11/14 17:26
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static  String getFileName(String path) {
        if (path.isEmpty()) {
            return "";
        } else {
            boolean isContains = path.contains("/");
            if (isContains) {
                int i = path.lastIndexOf("/");
                return path.substring(i + 1);
            } else {
                return path;
            }
        }
    }

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        String fileName = fileUtils.getFileName("/storage/emulated/0/360/sdk/persistence/3416a75f4cea9109507cacd8e2f2aefc");
        System.out.println(fileName);
    }
}
