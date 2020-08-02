package com.ngyb.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：南宫燚滨
 * 描述：封装的是使用gson解析json的方法
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/26 09:21
 */
public class GsonUtils {

    /**
     * 把一个map变成json字符串
     *
     * @param map
     * @return
     */
    public static String parseMapToJson(Map<?, ?> map) {
        try {
            Gson gson = new Gson();
            return gson.toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把一个json字符串变成对象
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T parseJsonToBean(String json, Class<T> clz) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(json, clz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 把json字符串变成map
     *
     * @param json
     * @return
     */
    public static HashMap<String, Object> parseJsonToMap(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {

        }.getType();
        HashMap<String, Object> map = null;
        try {
            map = gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 把json字符串变成集合
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonToList(String json, Type type) {
        Gson gson = new Gson();
        List<T> list = gson.fromJson(json, type);
        return list;
    }

    public static <T> List<T> parseJsonToListNew(String json, Class<T> clz) {
        Gson gson = new Gson();
        ArrayList<T> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                T t = gson.fromJson(jsonArray.optJSONObject(i).toString(), clz);
                list.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取json串中的某个字段的值
     * 注意，只能获取同一个层级的value
     *
     * @param json
     * @param key
     * @return
     */
    public static String getFieldValue(String json, String key) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        if (!json.contains(key)) {
            return "";
        }
        JSONObject jsonObject = null;
        String value = null;
        try {
            jsonObject = new JSONObject(json);
            value = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
}
