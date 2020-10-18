package com.xlhj.security.exception;

import lombok.Data;

/**
 * @ClassName UserNotFoundException
 * @Description 统一异常处理
 * @Author liucaijing
 * @Date 2020/10/18 12:05
 * @Version 1.0
 */
@Data
public class UserException extends RuntimeException {

    private static final long serialVersionUID = 2940550221210469791L;

    /**错误码*/
    private String code;

    /**错误码对应参数*/
    private Object[] args;

    /**错误消息*/
    private String message;

    public UserException(String code, Object[] args, String message) {
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public UserException(String message) {
        this(null, null, message);
    }
}
