package com.deepbluebi.basic.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述： 通用的请求
 *
 * @author caojing
 * @create 2019-08-14-11:40
 */
@Slf4j
public class OKHttpUtils {

    private static final MediaType JSONTYPE = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient singleton;

    private static OkHttpClient getInstance() {
        if (singleton == null) {
            synchronized (OKHttpUtils.class) {
                if (singleton == null) {
                    singleton = new OkHttpClient();
                }
            }
        }
        return singleton;
    }

    private static OkHttpClient getBaseAuthInstance(String userName, String password) {
        if (singleton == null) {
            synchronized (OKHttpUtils.class) {
                if (singleton == null) {
                    singleton = new OkHttpClient.Builder().authenticator(new Authenticator() {
                        @Override
                        public Request authenticate(Route route, Response response) throws IOException {
                            String credential = Credentials.basic(userName, password);
                            return response.request().newBuilder().header("Authorization", credential).build();
                        }
                    }).build();
                }
            }
        }
        return singleton;
    }

    /**
     * HTTP Basic Auth
     *
     * @param userName
     * @param password
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String post(String userName, String password, String url, String json) throws IOException {
        Request request = new Request.Builder().url(url).post(RequestBody.create(JSONTYPE, json)).build();
        Response response = getBaseAuthInstance(userName, password).newCall(request).execute();
        return response.body().string();
    }

    /**
     * 正常的post请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String postNoBaseAuth(String url, String json) {
        try {
            Response response = getInstance().newCall(createRequest(url, json)).execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("postError", e);
            return null;
        }

    }

    private static Request createRequest(String url, String json) {
        return new Request.Builder().url(url).post(RequestBody.create(JSONTYPE, json)).build();
    }

    public static String postForForm(String userName, String password, Map<String, String> bodyMap, String url) {
        FormBody.Builder builder = new FormBody.Builder();
        bodyMap.forEach((key, value) -> {
            builder.add(key, value);
        });
        RequestBody requestBody = builder.build();
        try {
            Response response = getBaseAuthInstance(userName, password).newCall(new Request.Builder().url(url).post(requestBody).build()).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error("postError", e);
            return null;
        }
    }

    /**
     * post请求 带有自定义请求头
     *
     * @param url
     * @param json
     * @param headMap
     * @return
     */
    public static String postForMoreHead(String url, String json, Map<String, String> headMap) {
        try {
            Request.Builder builder = new Request.Builder();
            headMap.forEach((key, value) -> {
                builder.addHeader(key, value);
            });
            Response response = getInstance().newCall(builder.post(RequestBody.create(JSONTYPE, json)).url(url).build()).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error("postError", e);
            return null;
        }

    }

    /**
     * post请求带文件附件内容（文件key是file）
     *
     * @param url      请求地址
     * @param file     文件二进制内容
     * @param map      其余参数
     * @param fileName 文件名称
     * @return
     */
    public static String postFileFromData(String url, byte[] file, Map<String, Object> map, String fileName) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName,
                        RequestBody.create(MediaType.parse("application/octet-stream"), file));
        for (Map.Entry<String, Object> item : map.entrySet()) {
            builder.addFormDataPart(item.getKey(), item.getValue().toString());
        }
        MultipartBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "multipart/form-data")
                .build();
        try {
            Response response = getInstance().newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error("postFileFromData error", e);
            return null;
        }

    }

    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("scope", "base");
//        map.put("grant_type", "client_credentials");
//        System.out.println(postForForm("a670b8f039594a829460440ef173fa1d", "5cb0daa6f4384814a0243490ae55c26d", map, "https://dev-sso.deepblueai.com/api/oauth/token"));
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsaWNlbnNlIjoiQ29weXJpZ2h0IChjKSAyMDE5IGRlZXBibHVlIiwic2NvcGUiOlsiYmFzZSJdLCJleHAiOjE1NjcwNDQwNjgsImp0aSI6Ijk3YjQzYWQxLTc2MzMtNDIyMS04OTZiLWMyNDBiMzQyNjUzZiIsImNsaWVudF9pZCI6ImE2NzBiOGYwMzk1OTRhODI5NDYwNDQwZWYxNzNmYTFkIn0.PbYkVaxLsQSgHiqkLr-A_ObGvIqNiiaibtimlILcsLg");

    }


}
