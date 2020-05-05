package com.ngyb.utils.encryption;

import android.util.Log;

import com.ngyb.utils.StreamUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/5 09:59
 */
public class FrequencyAnalysis {
    public static final char MAGIC_CHAR = 'e';
    public static final int DE_MAX_FILE = 4;
    private static final String TAG = "FrequencyAnalysis";

    public static void printCharCount(String path) {
        String data = StreamUtils.file2String(path);
        List<Entry<Character, Integer>> mapList = getMaxCountChar(data);
        for (Entry<Character, Integer> entry : mapList) {
            System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");
        }
    }

    public static void encryptFile(String srcFile, String destFile, int key) {
        String artile = StreamUtils.file2String(srcFile);
        Log.e(TAG, "encryptFile: " + artile);
        String encryptData = Kaiser.kaiserEncrypt(key, artile);
        Log.e(TAG, "encryptFile: " + encryptData);
        StreamUtils.string2File(encryptData, destFile);
    }

    public static void decryptCaesarCode(String input, String destPath) {
        int desCount = 0;
        List<Entry<Character, Integer>> mapList = getMaxCountChar(input);
        for (Entry<Character, Integer> entry : mapList) {
            if (desCount >= DE_MAX_FILE) {
                break;
            }
            ++desCount;
            int key = entry.getKey() - MAGIC_CHAR;
            String decrypt = Kaiser.kaiserDecryption(key, input);
            int i = destPath.lastIndexOf(".");
            if (i != -1) {
                String start = destPath.substring(0, i);
                String end = destPath.substring(i);
                String fileName = start + "_de_" + desCount + end;
                StreamUtils.string2File(decrypt, fileName);
            }
        }
    }

    /**
     * @param data
     * @return 统计String里出现最多的字符
     */
    public static List<Entry<Character, Integer>> getMaxCountChar(String data) {
        Map<Character, Integer> map = new HashMap<>();
        char[] array = data.toCharArray();
        for (char c : array) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                Integer count = map.get(c);
                map.put(c, count + 1);
            }
        }
        //获取最大值
        int maxCount = 0;
        for (Entry<Character, Integer> entry : map.entrySet()) {
            //不统计空格
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }
        //map转换成list便于排序
        ArrayList<Entry<Character, Integer>> mapList = new ArrayList<>(map.entrySet());
        //根据字符出现次数排序
        Collections.sort(mapList, new Comparator<Entry<Character, Integer>>() {
            @Override
            public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return mapList;
    }

    public static void main(String[] args) {
        List<Entry<Character, Integer>> mapList = getMaxCountChar("abcdddddddeeeesssssfabd");
        for (Entry<Character, Integer> entry : mapList) {
            System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");
        }
    }
}
