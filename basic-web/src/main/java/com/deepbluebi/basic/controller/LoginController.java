package com.deepbluebi.basic.controller;

import com.deepbluebi.basic.bean.po.user.AipSaasUserBeanDO;
import com.deepbluebi.basic.bean.base.LoginResponseDto;
import com.deepbluebi.basic.bean.base.ResponseBean;
import com.deepbluebi.basic.common.redis.RedisUtils;
import com.deepbluebi.basic.common.utils.Tools;
import com.deepbluebi.basic.config.jwt.JwtUtil;
import com.deepbluebi.basic.dao.aipSaasDao.AipSaasUserBeanDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author caojing
 * @create 2020-04-13-17:16
 */
@Api(tags = "登录接口")
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private AipSaasUserBeanDao userInfoDAO;
    @Resource
    private RedisUtils redisUtils;

    @ApiOperation("获取Token值")
    @PostMapping("/login")
    @ResponseBody
    public ResponseBean login(@ApiParam("账户") @RequestParam("userName") String userName,
                              @ApiParam("密码") @RequestParam("password") String password) {
        if (userName == null) {
            return new ResponseBean(1, "账户不能为空", "");
        } else if (password == null) {
            return new ResponseBean(1, "密码不能为空", "");
        } else {
            try {
                AipSaasUserBeanDO userBean = userInfoDAO.findByLoginName(userName);
                if (userBean == null) {
                    return new ResponseBean(1, "当前账号不存在", "");
                }
                password = Tools.MD5Pwd(userName, password);
                if (userBean.getPassword().equals(password)) {
                    String sign = JwtUtil.sign(userName, password);
                    redisUtils.setEx(sign, sign, 20 * 60, TimeUnit.SECONDS);
                    LoginResponseDto loginResponseDto = new LoginResponseDto();
                    loginResponseDto.setToken(sign);
                    loginResponseDto.setUserCode(userBean.getAipSaasCode());
                    return new ResponseBean(0, "登录成功", loginResponseDto);
                } else {
                    return new ResponseBean(1, "密码错误", "");
                }
            } catch (Exception e) {
                log.error("登陆失败", e);
                return new ResponseBean(1, "登陆失败", "");
            }
        }

    }


    @ApiOperation("注销接口")
    @PostMapping("/loginOut")
    @ResponseBody
    public ResponseBean checkSmsCode(HttpServletRequest request) {
        String token = request.getHeader("Authentication");
        if (token == null) {
            log.info("注销接口:EmailLoginController[checkSmsCode] token is null");
            return new ResponseBean(1, "传入token值", "");
        }
        // 解密获得username，用于和数据库进行对比
        redisUtils.delete(token);
        return new ResponseBean(0, "登出成功", "");

    }
}
