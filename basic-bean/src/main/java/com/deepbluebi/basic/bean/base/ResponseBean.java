package com.deepbluebi.basic.bean.base;

import io.swagger.annotations.ApiModelProperty;

/**
 * 描述：统一返回前端的实体类（方便以后前后台分离）
 *
 * @author caojing
 * @create 2019-06-21-13:56
 */
public class ResponseBean<T> {
    /**
     * 状态码，0 success,1 fail 3第一次登陆
     */
    @ApiModelProperty("状态码，0 success,1 fail,2 wait")
    private int code = 0;

    /**
     * 返回信息
     */
    @ApiModelProperty("返回信息")
    private String msg;

    /**
     * 返回的数据
     */
    @ApiModelProperty("返回数据")
    private T data;

    public ResponseBean() {

    }

    public ResponseBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void buildSuccessResponse(T data) {
        this.code = 0;
        this.data = data;
        this.msg = "成功";
    }

    public void buildFailedResponse() {
        this.code = 1;
        this.msg = "失败";
    }

    public void buildFailedResponse(String msg) {
        this.code = 1;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
