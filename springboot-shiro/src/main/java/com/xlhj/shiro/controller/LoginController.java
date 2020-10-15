package com.xlhj.shiro.controller;

import com.xlhj.shiro.common.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/15 20:55
 * @Version 1.0
 */
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    public HttpServletRequest request;

    @GetMapping("/login/{username}/{password}")
    @ApiOperation(value = "用户登录")
    public AjaxResult login(@PathVariable String username, @PathVariable String password) {
        if (username == null || password == null) {
            return AjaxResult.error().message("用户不存在!");
        }
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return AjaxResult.ok().message("登录成功");
        } catch (IncorrectCredentialsException e) {
            return AjaxResult.error().message("用户名或密码不正确!");
        } catch (UnknownAccountException e) {
            return AjaxResult.error().message("账户不存在!");
        } catch (LockedAccountException e) {
            return AjaxResult.error().message("账户被锁定!");
        } catch (DisabledAccountException e) {
            return AjaxResult.error().message("账户被禁用!");
        } catch (ExcessiveAttemptsException e) {
            return AjaxResult.error().message("用户名或密码错误次数太多!");
        } catch (AuthenticationException e) {
            return AjaxResult.error().message("验证未通过!");
        } catch (Exception e) {
            return AjaxResult.error().message("验证未通过!");
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/toLoginView";
    }
}
