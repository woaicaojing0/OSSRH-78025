package com.deepbluebi.basic.controller;

import com.deepbluebi.basic.bean.po.user.AipSaasUserBeanDO;
import com.deepbluebi.basic.bean.base.ResponseBean;
import com.deepbluebi.basic.bean.vo.user.AipSaasUserResponseVO;
import com.deepbluebi.basic.common.utils.Tools;
import com.deepbluebi.basic.config.jwt.JwtUtil;
import com.deepbluebi.basic.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：
 *
 * @author caojing
 * @create 2020-04-13-17:24
 */
@Api(tags = "测试接口")
@Controller
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    public UserService userService;

    @ApiOperation("获取系统用户信息")
    @PostMapping("/getSystemUserInfo")
    @ResponseBody
    @RequiresAuthentication
    public ResponseBean<AipSaasUserResponseVO> getAipSaasUserInfo() {
        AipSaasUserBeanDO bean = JwtUtil.getUserBean();
        AipSaasUserResponseVO userBean = userService.findByLoginName(bean.getName());
        return Tools.buildResSuccess(userBean);
    }
}
