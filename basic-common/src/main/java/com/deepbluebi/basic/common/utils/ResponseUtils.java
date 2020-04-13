package com.deepbluebi.basic.common.utils;

import com.alibaba.fastjson.JSON;
import com.deepbluebi.basic.bean.base.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wk on 2019/5/16
 *
 * @author wk
 */
public class ResponseUtils {

    public static String printSuccessResult() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", RequestStatusEnum.RequestSuccess.getCode());
        map.put("message", RequestStatusEnum.RequestSuccess.getMsg());
        return JSON.toJSONString(map);
    }

    public static String printSuccessResult(Object data) {
        ResponseBean ahSaasResult = new ResponseBean(
                RequestStatusEnum.RequestSuccess.getCode(), RequestStatusEnum.RequestSuccess.getMessage(),data);
        return JSON.toJSONString(ahSaasResult);
    }

    public static String printEntity(Object data) {
        return JSON.toJSONString(data);
    }

    public static String printMapSuccessResult(String key, Object value) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", RequestStatusEnum.RequestSuccess.getCode());
        map.put("message", RequestStatusEnum.RequestSuccess.getMsg());
        map.put(key, value);
        return JSON.toJSONString(map);
    }

    public static String printErrorResult(int code, String message, Object data) {
        ResponseBean ahSaasResult = new ResponseBean(code, message,data);
        return JSON.toJSONString(ahSaasResult);
    }

    public static String printErrorResult(int code, String messageå) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", code);
        map.put("message", messageå);
        return JSON.toJSONString(map);
    }

    public static String printErrorResult(RequestStatusEnum sassErrorCode) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", sassErrorCode.getCode());
        map.put("message", sassErrorCode.getMsg());
        return JSON.toJSONString(map);
    }

    public static String printLoginSuccessTitle() {
        return "登陆成功";
    }
}
