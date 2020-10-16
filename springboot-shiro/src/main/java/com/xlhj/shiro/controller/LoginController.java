package com.xlhj.shiro.controller;

import cn.hutool.core.util.StrUtil;
import com.xlhj.shiro.common.AjaxResult;
import com.xlhj.shiro.exception.UserNotExistsException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/15 20:55
 * @Version 1.0
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            logger.info("登录成功!");
            return AjaxResult.ok().message("登录成功!");
        } catch (UnknownAccountException e) {
            return AjaxResult.error().message("用户名或密码不正确!");
        } catch (IncorrectCredentialsException e) {
            return AjaxResult.error().message("用户名或密码不正确!");
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "unauth";
    }
}
