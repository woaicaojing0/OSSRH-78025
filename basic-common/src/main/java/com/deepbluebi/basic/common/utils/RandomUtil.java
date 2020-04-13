package com.deepbluebi.basic.common.utils;

import java.util.*;

/**
 * Created by Vic on 2017/8/14.
 */
public class RandomUtil {
    /**
     * 时间戳
     *
     * @return
     */
    public static String getMSecond() {
        long t = System.currentTimeMillis();
        return t + "";
    }

    /**
     * 时间戳加随机数
     *
     * @return
     */
    public static String getMSecondRand() {
        Random rand = new Random();
        long t = System.currentTimeMillis();
        return Long.valueOf(t * 1000L + (long) (rand.nextInt(900) + 100)) + "";
    }

    /**
     * 得到32位的uuid
     *
     * @return
     */
    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * list 转化为分隔符分割的字符串
     *
     * @param list
     * @param separator
     * @return
     */
    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * list 转化为分隔符分割的字符串
     *
     * @param result
     * @param separator
     * @return
     */
    public static List<String> StringToList(String result, String separator) {
        if (result == null) {
            return new ArrayList<>();
        }
        String str[] = result.split(separator);
        return Arrays.asList(str);
    }
}
