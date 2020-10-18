package com.xlhj.security.controller;

import com.xlhj.security.common.AjaxResult;
import com.xlhj.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassName LoginController
 * @Description 登录
 * @Author liucaijing
 * @Date 2020/10/18 20:01
 * @Version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    private SysUserService userService;

    public String login(String username, String password) {
        String token = userService.login(username, password);
        return token;
    }
}
