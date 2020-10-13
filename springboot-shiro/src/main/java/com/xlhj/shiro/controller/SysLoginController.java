package com.xlhj.shiro.controller;

import cn.hutool.core.util.StrUtil;
import com.xlhj.shiro.common.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lcj
 * @Date: 2020/10/13 13:38
 * @Description: 登录
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/shiro")
public class SysLoginController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("msg", "Hello Shiro");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "unauth";
    }
}
