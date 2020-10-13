package com.xlhj.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lcj
 * @Date: 2020/10/13 15:31
 * @Description: TODO
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/shiro/user")
public class SysUserController {

    @PostMapping("/add")
    public String add(Model model) {
        return "user/add";
    }

    @PostMapping("/update")
    public String update(Model model) {
        return "user/update";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码不正确");
            return "login";
        }
    }
}
