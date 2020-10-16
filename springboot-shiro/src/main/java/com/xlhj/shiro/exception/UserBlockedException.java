package com.xlhj.shiro.exception;

import lombok.Getter;

/**
 * @Author: lcj
 * @Date: 2020/10/16 10:41
 * @Description: TODO
 * @Version: 0.0.1
 */
@Getter
public class UserBlockedException extends RuntimeException {

    private String message;

    public UserBlockedException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public UserBlockedException() {
        super("user.is.blocked");
    }
}
