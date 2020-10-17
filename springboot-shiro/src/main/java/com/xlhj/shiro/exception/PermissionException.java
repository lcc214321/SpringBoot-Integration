package com.xlhj.shiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName PermissionException
 * @Description 异常处理
 * @Author liucaijing
 * @Date 2020/10/17 16:43
 * @Version 1.0
 */
@ControllerAdvice
public class PermissionException {

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception e) {
        return "unauth";
    }
}
