package com.xlhj.shiro.controller;

import com.xlhj.shiro.common.AjaxResult;
import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.service.SysUserService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lcj
 * @Date: 2020/10/13 15:31
 * @Description: TODO
 * @Version: 0.0.1
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController {

    private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService userService;

    @PostMapping("/list")
    @RequiresPermissions("system:user:list")
    public AjaxResult selectUserList() {
        return AjaxResult.ok().message("查询用户列表");
    }
}
