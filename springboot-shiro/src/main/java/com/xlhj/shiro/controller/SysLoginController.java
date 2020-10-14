package com.xlhj.shiro.controller;

import cn.hutool.core.util.StrUtil;
import com.xlhj.shiro.common.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lcj
 * @Date: 2020/10/13 13:38
 * @Description: 登录
 * @Version: 0.0.1
 */
@Controller
public class SysLoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult doLogin(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return AjaxResult.ok();
        } catch (AuthenticationException e) {
            String message = "用户名或密码不正确!";
            if (StrUtil.isNotEmpty(e.getMessage())) {
                message = e.getMessage();
            }
            return AjaxResult.error().message(message);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
