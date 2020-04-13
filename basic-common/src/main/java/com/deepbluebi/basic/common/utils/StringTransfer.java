package com.deepbluebi.basic.common.utils;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述：
 *
 * @author zhanghao
 * @create 2018-11-19-11:09
 */
public class StringTransfer {

    static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static byte BLANK = " ".getBytes(DEFAULT_CHARSET)[0];

    /**
     * 按照byte获取字符串前num个byte字符
     *
     * @param s
     * @param num
     * @return
     */
    public static String getStrBylengthForByte(String s, int num) {
        int changdu = s.getBytes().length;
        if (changdu > num) {
            s = s.substring(0, s.length() - 1);
            s = getStrBylengthForByte(s, num);
        }
        return s;
    }

    /**
     * Base64编码解码
     *
     * @param veinData
     * @return
     */
    public static String byteToString(byte[] veinData) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(veinData);
    }

    /**
     * 字符串转换成字节数组（手脉数据转换）
     *
     * @param hexString
     * @return
     */
    public static byte[] stringToByte(String hexString) {
//		byte[] tmpByte = new byte[hexString.length() / 8];
//		for (int i = 0; i < hexString.length() / 8; i++) {
//			String str = hexString.substring(i * 8, (i + 1) * 8);
//			if (str.substring(0, 1).equals("1")) {
//				str = "0" + str.substring(1);
//				tmpByte[i] = Byte.parseByte(str, 2);
//				tmpByte[i] |= 128;
//			} else {
//				tmpByte[i] = Byte.parseByte(str, 2);
//			}
//		}
//		return tmpByte;

        //现在改用Base64编码解码 by wangshuai
        byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(hexString.getBytes());
        return result;
    }

    /**
     * 比较两个时间，如果end比start小，end的小时数加24后返回，
     * 否则返回end
     *
     * @param start
     * @param end
     * @return
     */
    public static String timeToNextDay(String start, String end) {
        return start.compareTo(end) > 0 ? String.format("%02d", (Integer.parseInt(end.split(":")[0]) + 24))
                + ":" + end.split(":")[1] : end;
    }

    /**
     * 检查时间，如果时间超过24小时，将小时数减去24，返回时间 否则返回原值 25:10 返回 01:10
     *
     * @param dayTime
     * @return
     */
    public static String getTimeOfNextDay(String dayTime) {
        return dayTime.compareTo("24:00") >= 0 ? String.format("%02d", (Integer.parseInt(dayTime.split(":")[0]) - 24))
                + ":" + dayTime.split(":")[1] : dayTime;
    }


    /**
     * 时间转换成分钟数
     *
     * @param time 10:30
     * @return 630
     */
    public static int getMinutesOfTime(String time) {
        if (time.contains(":")) {
            return Integer.valueOf(time.split(":")[0]) * 60
                    + Integer.valueOf(time.split(":")[1]);
        } else {
            return 0;
        }
    }


    public static final String leftBlankPadForUtf8(String str, int length) {
        try {
            return new String(regularBlankBytes(str.getBytes(DEFAULT_CHARSET), length), DEFAULT_CHARSET);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 标准化字节长度，不满length补空
     *
     * @param ins
     * @param length
     * @return
     */
    public static final byte[] regularBlankBytes(byte[] ins, int length) {

        if (ins == null) {
            return null;
        }

        if (ins.length >= length) {
            return ins;
        }

        List<Byte> bytes = new LinkedList<>();
        for (Byte temp : ins) {
            bytes.add(temp);
        }

        while (bytes.size() < length) {
            bytes.add(0, BLANK);
        }

        byte[] result = new byte[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = bytes.get(i);
        }

        return result;
    }

}