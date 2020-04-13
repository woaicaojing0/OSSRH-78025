package com.deepbluebi.basic.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by wk on 2019/5/5
 *
 * @author wk
 */
public class ServiceUtils {

    private static final String numText = "0123456789";

    private static final String text = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 生成一个16位用户id
     */
    public static String genUserId() {
        int length = 12;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("2088");
        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(numText.length());
            stringBuilder.append(numText.charAt(index));
        }
        return stringBuilder.toString();
    }

    /**
     * 生成一个32位用户令牌
     */
    public static String genUserToken() {
        int length = 18;
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(time);
        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(text.length());
            stringBuilder.append(text.charAt(index));
        }
        return stringBuilder.toString();
    }
}
