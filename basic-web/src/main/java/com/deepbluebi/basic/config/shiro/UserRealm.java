package com.deepbluebi.basic.config.shiro;


import com.deepbluebi.basic.bean.po.user.AipSaasUserBeanDO;
import com.deepbluebi.basic.common.redis.RedisUtils;
import com.deepbluebi.basic.config.jwt.JwtToken;
import com.deepbluebi.basic.config.jwt.JwtUtil;
import com.deepbluebi.basic.dao.aipSaasDao.AipSaasUserBeanDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 集成Shiro核心内容:
 * ShiroFilterFactory，Shiro过滤器工程类，具体的实现类是：ShiroFilterFactoryBean，此实现类是依赖于SecurityManager安全管理器。主要配置Filter就好。
 * SecurityManager，Shiro的安全管理，主要是身份认证的管理，缓存管理，cookie管理，所以在实际开发中我们主要是和SecurityManager进行打交道的。
 * Realm,用于身份信息权限信息的验证。开发时集成AuthorizingRealm，重写两个方法:doGetAuthenticationInfo(获取即将需要认真的信息)、doGetAuthorizationInfo(获取通过认证后的权限信息)。
 * 参考资料 http://www.infocool.net/kb/Apache/201609/190994.html
 * http://blog.csdn.net/catoop/article/details/50520958
 *
 * @ClassName: UserRealm
 * @Description:TODO(shiro权限验证)
 * @author: wangshuai
 * @date: 2017年3月30日 下午9:08:21
 * @Copyright: 2017 www.deepbluebi.com Inc. All rights reserved.
 */
@Slf4j
@Service
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private AipSaasUserBeanDao userBeanDao;
    @Resource
    private RedisUtils redisUtils;

    //
//    /**
//     * 大坑！，必须重写此方法，不然Shiro会报错
//     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 获取通过认证后的权限信息
     * 只有当需要检测用户权限的时候才会调用此方法
     * principals 也就是在doGetAuthenticationInfo 这个方法中最后SimpleAuthenticationInfo这个方法中设置的userBean对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AipSaasUserBeanDO sysUserBean = (AipSaasUserBeanDO) principals.getPrimaryPrincipal();
        log.info("----------通过后认证权限信息 ---------  currentLoginName=" + sysUserBean.getName());
        List<String> userRoles = new ArrayList<String>();
        // 从数据库中获取当前登录用户的详细信息(我这里简单一点，只要区分主账号和子账号就可以了)
        // 为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userRoles);
        //暂时不需要这个权限
//        Set<String> permission = new HashSet<>();
//        permission.add("admin");
//        authorizationInfo.addStringPermissions(permission);

        return authorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * 这边抛出异常之后，会在上面一层捕获到，具体是在isAccessAllowed 方法中捕获
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        log.info("----------认证用户登录信息 ---------");
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String signOld = redisUtils.get(token);
        if (signOld == null) {
            throw new AuthenticationException("token timeout");
        }
        String username = JwtUtil.getUsername(signOld);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }
        AipSaasUserBeanDO userBean = userBeanDao.findByLoginName(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JwtUtil.verify(signOld, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        String signNew = JwtUtil.sign(username, userBean.getPassword());
        redisUtils.setEx(token, signNew, 20 * 60, TimeUnit.SECONDS);

        return new SimpleAuthenticationInfo(userBean, token, getName());
    }

}
