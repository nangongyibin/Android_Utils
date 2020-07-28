package com.ngyb.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/28 10:53
 */
public class SmsUtils {
    /**
     * 发送短信
     *
     * @param context
     * @param phoneNumber
     * @param content
     */
    public static void sendSms(Context context, String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto:" + (TextUtils.isEmpty(phoneNumber) ? "" : phoneNumber));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", (TextUtils.isEmpty(content) ? "" : content));
        context.startActivity(intent);
    }

    /**
     * 获取手机短信并保存到xml中
     *
     * @param context
     */
    public static void getAllSms(Context context, String saveSmsInfoPath, String encoding) {
        // 获取短信
        // 获取内容解析者
        ContentResolver resolver = context.getContentResolver();
        // 获取内容提供者地址 sms  sms表的地址 null 不等
        // 获取查询路径
        Uri uri = Uri.parse("content://sms");
        // 查询操作
        //projection 查询的字段
        // selection 查询的条件
        //selectionArgs查询条件的参数
        // sortOrder 排序
        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
        //设置最大进度
        int count = cursor.getCount();//获取短信的个数
        //备份短信
        // 获取xml序列器
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try {
            // 设置xml文件保存的路径
            // os 保存的位置
            // encoding编码格式
            xmlSerializer.setOutput(new FileOutputStream(new File(saveSmsInfoPath)), encoding);
            //设置头信息
            // standalone 是否独立保存
            xmlSerializer.startDocument(encoding, true);
            //设置根标签
            xmlSerializer.startTag(null, "smss");
            //解析cursor
            while (cursor.moveToNext()) {
                SystemClock.sleep(3000);
                //设置短信的标签
                xmlSerializer.startTag(null, "sms");
                // 设置文本内容的标签
                xmlSerializer.startTag(null, "address");
                String address = cursor.getString(0);
                //设置文本内容
                xmlSerializer.text(address);
                xmlSerializer.endTag(null, "address");
                xmlSerializer.startTag(null, "date");
                String date = cursor.getString(1);
                xmlSerializer.text(date);
                xmlSerializer.endTag(null, "date");
                xmlSerializer.startTag(null, "type");
                String type = cursor.getString(2);
                xmlSerializer.text(type);
                xmlSerializer.endTag(null, "type");
                xmlSerializer.startTag(null, "body");
                String body = cursor.getString(3);
                xmlSerializer.text(body);
                xmlSerializer.endTag(null, "body");
                xmlSerializer.endTag(null, "sms");
            }
            xmlSerializer.endTag(null, "smss");
            xmlSerializer.endDocument();
            // 将数据刷新到文件中
            xmlSerializer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
    }
}
