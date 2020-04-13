package com.deepbluebi.basic.bean.base;

/**
 * 描述：
 *
 * @author caojing
 * @create 2019-11-19-12:35
 */
public class LoginResponseDto {
    String token;
    String userCode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
