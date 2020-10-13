package com.xlhj.shiro.exception;

import lombok.Data;

/**
 * @Author: lcj
 * @Date: 2020/10/13 11:46
 * @Description: 同一处理异常
 * @Version: 0.0.1
 */
@Data
public class XlhjException extends RuntimeException {

    /**错误码*/
    private String errorCode;

    /**错误信息*/
    private String errorMessage;

    public XlhjException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
