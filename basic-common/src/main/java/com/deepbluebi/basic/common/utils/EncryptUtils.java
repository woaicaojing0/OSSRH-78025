package com.deepbluebi.basic.common.utils;

import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Security;
import java.util.Arrays;

/**
 * 描述：加密算法工具类
 * 加密与加密结果验证（不可逆算法)
 *
 * @author zhanghao
 * @create 2019-07-19-10:44
 */
public class EncryptUtils {

    private static final String ENCODING = "UTF-8";
    static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    static {
        Security.addProvider(new BouncyCastleProvider());

    }


    /**
     * sm3算法加密
     *
     * @param paramStr 待加密字符串
     * @return 返回加密后16进制字符串
     */
    public static String encryptSm3(String paramStr) {
        String resultHexString = "";
        try {
            byte[] srcData = paramStr.getBytes(ENCODING);
            byte[] hash = hashSM3(srcData);
            resultHexString = ByteUtils.toHexString(hash);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultHexString;
    }


    /**
     * 通过密钥进行加密
     * 用SM3算法加密
     *
     * @param key    密钥
     * @param srcStr 待加密的byte数组
     * @return
     */
    public static String hmacSm3(String key, String srcStr) {
        KeyParameter keyParameter = new KeyParameter(key.getBytes(DEFAULT_CHARSET));
        byte[] srcData = srcStr.getBytes(DEFAULT_CHARSET);
        SM3Digest digest = new SM3Digest();

        // SHA256Digest digest1 = new SHA256Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return ByteUtils.toHexString(result);
    }


    public static String hmacSm3(String key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key.getBytes(DEFAULT_CHARSET));
        SM3Digest digest = new SM3Digest();

        // SHA256Digest digest1 = new SHA256Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return ByteUtils.toHexString(result);
    }


    public static String md5(byte[] srcData) {

        MD5Digest digest = new MD5Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] newHash = new byte[digest.getDigestSize()];
        digest.doFinal(newHash, 0);
        return ByteUtils.toHexString(newHash);
    }


    private static byte[] hashSM3(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] newHash = new byte[digest.getDigestSize()];
        digest.doFinal(newHash, 0);
        return newHash;
    }

    /**
     * 判断校验
     *
     * @param srcStr       原字符串
     * @param sm3HexString 摘要
     * @return
     */
    public static boolean verifySm3(String srcStr, String sm3HexString) {
        boolean flag = false;
        try {
            byte[] srcData = srcStr.getBytes(ENCODING);
            byte[] sm3Hash = ByteUtils.fromHexString(sm3HexString);
            byte[] newHash = hashSM3(srcData);
            if (Arrays.equals(newHash, sm3Hash)) {
                flag = true;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return flag;
    }


    /**
     * @param myKey    本地key值
     * @param dataStr  待加密的数据
     * @param checkKey 传来的摘要
     * @return
     */
    public static boolean verifyHashSm(String myKey, String dataStr, String checkKey) {

        String result = hmacSm3(myKey, dataStr);

        return result.equals(checkKey);


    }

    public static boolean verifyMd5(String digest, byte[] srcData) {
        String result = md5(srcData);
        return result.equals(digest);
    }


    public static void main(String[] args) {
        //测试
        String test = "{\"device\":{\"EmbeddedVersionA\":\"�.�.\u007F\",\"EmbeddedVersionB\":\"U.U.U\",\"address\":\"192.168.1.51\",\"devSN\":\"D3000001\",\"direction\":\"out\",\"identificationType\":\"0\",\"mac\":\"E8:6A:64:54:B6:BA\",\"mask\":\"255.255.255.0\",\"model\":\"D3\",\"softVersion\":\"1.0.1\",\"workMode\":\"IotMode\"}}";

        String key = "1564652203718palmGate";


        String hex = EncryptUtils.encryptSm3(test);
        System.out.println("sm3: " + hex);

        try {
            String result = EncryptUtils.hmacSm3(key, test);

            System.out.println("hmac:" + result);

       /*     byte[] data = "1111111111111abcd".getBytes(ENCODING);
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, "HmacSHA256");
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            //用给定密钥初始化 Mac 对象
            mac.init(secretKey);

            byte[] text = test.getBytes(ENCODING);
            //完成 Mac 操作
            byte[] result2 = mac.doFinal(text);
            System.out.println("aa:" + ByteUtils.toHexString(result2));*/

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


}