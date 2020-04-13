package com.deepbluebi.basic.config.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 描述：这边重写了AuthenticationToken，对应的是 UserRealm中身份验证的过程，重写原来的getPrincipal和getCredentials方法。
 * getPrincipal 是获取身份（也就是用户名）
 * getCredentials 是凭据
 *
 * @author caojing
 * @create 2019-11-19-10:00
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
