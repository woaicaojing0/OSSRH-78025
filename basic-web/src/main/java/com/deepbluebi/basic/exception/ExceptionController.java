package com.deepbluebi.basic.exception;

import com.deepbluebi.basic.bean.base.ResponseBean;
import com.deepbluebi.basic.common.utils.Tools;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cj
 * 异常捕获，遵循restful风格
 */
@RestControllerAdvice
public class ExceptionController {
    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 捕捉shiro的异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseBean handle401(ShiroException e) {
        if (e instanceof UnauthorizedException) {
            return Tools.buildResFail("无对应权限");
        } else if (e instanceof AuthenticationException) {
            return Tools.buildResFail(e.getMessage());
        }
        return new ResponseBean(401, "Shiro错误，" + e.getMessage(), null);
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseBean handle403() {
        return new ResponseBean(1, "非法访问", null);
    }

    /**
     * 捕捉其他所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean globalException(HttpServletRequest request, Throwable ex) {
        logger.error("异常：", ex);
        return new ResponseBean(1, ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

