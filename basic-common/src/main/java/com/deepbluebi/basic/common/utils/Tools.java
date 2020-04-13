package com.deepbluebi.basic.common.utils;

import com.deepbluebi.basic.bean.base.ResponseBean;
import com.deepbluebi.basic.bean.base.ResponsePageListBean;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 描述：
 *
 * @author caojing
 * @create 2019-06-21-15:48
 */
public class Tools {
    /**
     * @param iStrm
     * @return
     * @throws IOException
     */
    public static byte[] InputStreamToByte(InputStream iStrm) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = iStrm.read()) != -1) {
            byteStream.write(ch);
        }
        byte imgdata[] = byteStream.toByteArray();
        byteStream.close();
        return imgdata;
    }

    /**
     * 递归压缩图片到指定大小以内
     *
     * @param bytes       图片字节流
     * @param desFileSize 目标大小 Kb
     * @param accuracy    递归压缩比率
     * @return
     * @throws IOException
     */
    public static byte[] compressPicCycle(byte[] bytes, long desFileSize, double accuracy) throws IOException {
        long srcFileSizeJPG = bytes.length;
//        System.out.println("原大小：" + srcFileSizeJPG);
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return bytes;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Thumbnails.of(new ByteArrayInputStream(bytes)).scale(accuracy).toOutputStream(baos);
        return compressPicCycle(baos.toByteArray(), desFileSize, accuracy);
    }


    /**
     * 页面数据返回
     *
     * @param list
     * @param totalElements
     * @return
     */
    public static ResponsePageListBean getResponsePageList(List<?> list, Long totalElements) {
        ResponsePageListBean responsePageListBean = new ResponsePageListBean();
        responsePageListBean.setData(list);
        responsePageListBean.setRecordsTotal(totalElements);
        responsePageListBean.setRecordsFiltered(totalElements);
        return responsePageListBean;
    }




    public static String getBasePath(HttpServletRequest httpServletRequest) {
        String path = httpServletRequest.getContextPath();
        String scheme = httpServletRequest.getScheme();
        String serverName = httpServletRequest.getServerName();
        int port = httpServletRequest.getServerPort();
        String basePath = scheme + "://" + serverName + ":" + port + path;
        return basePath;
    }

    public static String getFileName(String fileNamePrefix) {
        StringBuilder fileName = new StringBuilder(fileNamePrefix);
        String templateSplitor = "_";
        String suffix = ".xls";
        fileName.append(templateSplitor).append(LocaleContextHolder.getLocale().getLanguage()).append(templateSplitor).append(LocaleContextHolder.getLocale().getCountry())
                .append(suffix);
        return fileName.toString();
    }

    /**
     * 构建请求返回成功消息
     *
     * @param
     * @return 请求返回数据
     */
    public static ResponseBean buildResSuccess() {
        return new ResponseBean(0, "成功", "");
    }

    /**
     * 构建请求返回成功消息
     *
     * @param msg 返回的信息
     * @return
     */
    public static ResponseBean buildResSuccess(String msg) {
        return new ResponseBean(0, msg, "");
    }

    /**
     * 构建请求返回成功消息
     *
     * @param data 返回的数据对象
     * @return 请求返回数据
     */
    public static ResponseBean buildResSuccess(Object data) {
        return new ResponseBean(0, "成功", data);
    }


    public static ResponseBean buildResFail() {
        return new ResponseBean(1, "失败", "");
    }


    public static ResponseBean buildResFail(String message) {
        return new ResponseBean(1, message, "");
    }


    public static ResponseBean buildResWait() {
        return new ResponseBean(2, "", "");
    }

    /**
     * 构建请求返回失败消息
     *
     * @param data 返回的数据对象
     * @return 请求返回数据
     */
    public static ResponseBean buildResFail(Object data) {
        return new ResponseBean(1, "", data);
    }

    public static Integer[] StringArrToIntArr(String[] arr) {
        if ("".equals(arr[0])) {
            return new Integer[0];
        } else {
            Integer[] intArr = new Integer[arr.length];
            for (int i = 0; i < arr.length; i++) {
                intArr[i] = Integer.valueOf(arr[i]);
            }
            return intArr;
        }
    }

    public static String MD5Pwd(String username, String pwd) {
        String md5Pwd = new SimpleHash("MD5", // 加密算法
                pwd, // 密码
                ByteSource.Util.bytes(username + "salt"), // salt盐 username + salt
                2 // 迭代次数
        ).toHex();
        return md5Pwd;
    }

    public static void main(String[] args) {
        System.out.println(MD5Pwd("test", "test123"));
    }
    /**
     * 生成随机用户名，数字和字母组成,
     */
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 2个元素中找出需要增加的元素
     *
     * @param oldList
     * @param newList 从这里面找出需要新增的
     * @return
     */
    public static List<String> findAddElement(List<String> oldList, List<String> newList) {
        List<String> addList = new ArrayList<>();
        for (String id : newList) {
            //老的数据不包含新的，那就要新增
            if (!(oldList.contains(id))) {
                addList.add(id);
            }
        }
        return addList;
    }

    /**
     * 2个元素中找出需要删除的元素
     *
     * @param oldList 从这里面找出需要删除的
     * @param newList
     * @return
     */
    public static List<String> findDeleteElement(List<String> oldList, List<String> newList) {
        return findAddElement(newList, oldList);
    }
}
