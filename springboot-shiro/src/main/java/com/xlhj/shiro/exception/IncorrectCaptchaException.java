package com.xlhj.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @Author: lcj
 * @Date: 2020/10/13 16:55
 * @Description: 验证码错误异常
 * @Version: 0.0.1
 */
public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }
}
