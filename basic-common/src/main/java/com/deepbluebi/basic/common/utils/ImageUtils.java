package com.deepbluebi.basic.common.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author caojing
 * @create 2019-07-08-10:46
 */
public class ImageUtils {
    static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    public static String FACE_IMAGE_COMPRESS = "faceImageCompress";
    public static String FACE_IMAGE = "faceImage";

    public static boolean base64StrToImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        try {
            // 解密
            byte[] b = Base64.decodeBase64(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            logger.error("ImageUtils[base64StrToImage] 出错: ", e);
            return false;
        }
    }

    /**
     * 图片压缩
     *
     * @param imgPath
     * @return Map<String, byte [ ]> 一共2个值：faceImage 和 faceImageCompress
     * faceImage：第一次压缩的图片
     * faceImageCompress :如果第一次压缩过后大于50m，继续压缩，否则和 faceImage一样大小
     */
    public static Map<String, byte[]> compressImage(String imgPath) {

        try {
            //保存原始图片
            final String path = ResourceUtils.getURL("classpath:") + imgPath;
            String filePath = path.replace(ResourceUtils.FILE_URL_PREFIX, "");
            InputStream inputStream = new FileInputStream(filePath);
            byte[] data = Tools.InputStreamToByte(inputStream);
            return compressImageByByte(data);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 图片压缩
     *
     * @param
     * @return Map<String, byte [ ]> 一共2个值：faceImage 和 faceImageCompress
     * faceImage：第一次压缩的图片
     * faceImageCompress :如果第一次压缩过后大于50m，继续压缩，否则和 faceImage一样大小
     */
    public static Map<String, byte[]> compressImageByByte(byte[] data) {
        Map<String, byte[]> stringMap = new HashMap<>();
        try {
            logger.info("开始压缩图片，原始图片大小：" + data.length);
            ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            //图片格式转成jpg
            Thumbnails.of(new ByteArrayInputStream(data)).outputFormat("jpg").scale(1).toOutputStream(dataStream);
            data = dataStream.toByteArray();
            byte[] face = Tools.compressPicCycle(data, 300, 0.75);
            logger.info("结束压缩图片，压缩后图片大小：" + data.length);
            stringMap.put(FACE_IMAGE, face);
            if (face.length > 10 * 1024) {
                logger.info("压缩后图片大小大于10kb，则进行第二次压缩。开始压缩：起始图片大小：" + data.length);
                byte[] faceCompress = Tools.compressPicCycle(data, 10, 0.75);
                logger.info("压缩后图片大小大于10kb，则进行第二次压缩。结束压缩：压缩后图片大小：" + data.length);
                stringMap.put(FACE_IMAGE_COMPRESS, faceCompress);
            } else {
                stringMap.put(FACE_IMAGE_COMPRESS, face);
            }
            return stringMap;
        } catch (Exception e) {
            logger.error("图片压缩异常，异常信息：" + e.getMessage());
            return null;
        }

    }
}
