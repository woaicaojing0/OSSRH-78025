package com.deepbluebi.basic.common.utils;




public enum RequestStatusEnum {
    RequestSuccess(200, "success"),
    RequestFile(203, "失败"),
    LOGIN_STATE_IS_INVLID(203,"登录状态初始化"),
    VERIFY_SIGN_FAIL(203 , "登录验证失败"),
    USER_IS_NOT_EXIST(203,"用户不存在"),
    SESSION_IS_ERROR(203,"缓存失败"),
    SERVER_ERROR(203,"服务失败")
    ;



    private Integer code;
    private String msg;


    RequestStatusEnum(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }


    public Integer getCode() {
        Integer code = this.code;
        return code;
    }

    public String getMessage() {
        String msg = this.msg;
        return msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static RequestStatusEnum getCode(int code) {
        for (RequestStatusEnum requestStatusEnum : RequestStatusEnum.values()) {
            if (code == requestStatusEnum.getCode()) {
                return requestStatusEnum;
            }
        }
        return null;
    }

}
