package com.xlhj.shiro.exception;

/**
 * @Author: lcj
 * @Date: 2020/10/13 11:53
 * @Description: TODO
 * @Version: 0.0.1
 */
public class UserNotExistsException extends XlhjException {

    public UserNotExistsException() {
        super("40000", "user.not.exists");
    }
}
