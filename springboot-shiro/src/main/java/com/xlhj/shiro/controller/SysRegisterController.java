package com.xlhj.shiro.controller;

import cn.hutool.core.util.StrUtil;
import com.xlhj.shiro.common.AjaxResult;
import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.service.SysUserService;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lcj
 * @Date: 2020/10/14 9:59
 * @Description: 用户注册
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/shiro")
public class SysRegisterController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public AjaxResult doRegister(@RequestBody SysUser user) {
        String message = userService.register(user);
        return StrUtil.isEmpty(message) ? AjaxResult.ok() : AjaxResult.error().message(message);
    }
}
