package com.xlhj.shiro.common;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Author: lcj
 * @Date: 2020/10/13 16:39
 * @Description: 登录验证字段
 * @Version: 0.0.1
 */
@Data
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    private String captcha;

    public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }
}
