package com.xlhj.shiro.exception;

import lombok.Getter;

/**
 * @Author: lcj
 * @Date: 2020/10/16 10:41
 * @Description: 用户不存在异常类
 * @Version: 0.0.1
 */
@Getter
public class UserNotExistsException extends RuntimeException {

    private String message;

    public UserNotExistsException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
