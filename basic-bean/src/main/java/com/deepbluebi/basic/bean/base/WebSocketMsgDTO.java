package com.deepbluebi.basic.bean.base;

/**
 * 描述：
 *
 * @author caojing
 * @create 2019-07-29-14:09
 */
public class WebSocketMsgDTO<T> {
    public WebSocketMsgDTO() {
    }

    public WebSocketMsgDTO(String UUID, Integer code, String msg) {
        this.UUID = UUID;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 唯一标识，根据uuid查询到连接，然后给指定的页面推送消息
     */
    private String UUID;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 返回状态 0 success,1 fail
     */
    private Integer code;

    private T data;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WebSocketMsgDTO{" +
                "UUID='" + UUID + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
