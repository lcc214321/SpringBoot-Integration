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
@RequestMapping("/user")
public class SysUserController {

    private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    public HttpServletRequest request;

    @Autowired
    private SysUserService userService;

    /**
     * 查询用户
     * @return
     */
    @RequestMapping("/searchUser")
    @RequiresPermissions("system:user:list")
    public String searchUser() {
        request.setAttribute("message", "查询用户");
        return "user.html";
    }

    /**
     * 修改用户
     * @return
     */
    @RequestMapping("/editUser")
    @RequiresPermissions("system:user:edit")
    public String editUser() {
        request.setAttribute("message", "修改用户");
        return "user.html";
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/deleteUser")
    @RequiresPermissions("system:user:remove")
    public String deleteUser() {
        request.setAttribute("message", "删除用户");
        return "user.html";
    }

    @RequestMapping("/unauthUser")
    @RequiresPermissions("system:user:unauth")
    public String unauthUser() {
        request.setAttribute("message", "无权限");
        return "user.html";
    }

    /**
     * 修改用户
     * @return
     */
    /*@RequestMapping("/addUser")
    @RequiresPermissions("system:user:add")
    public String addUser() {
        request.setAttribute("message", "新增用户");
        return "user.html";
    }*/

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    /*@GetMapping("/login/{username}/{password}")
    @ApiOperation(value = "用户登录")
    public AjaxResult login(@PathVariable String username, @PathVariable String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            logger.info("用户登录成功!");
            return AjaxResult.ok().message("登录成功!");
        } catch (UnknownAccountException e) {
            logger.info("用户登录失败!");
            return AjaxResult.error().message("用户名不存在!");
        } catch (IncorrectCredentialsException e) {
            logger.info("用户登录失败!");
            return AjaxResult.error().message("密码不正确!");
        }
    }*/

    /**
     * 查询用户信息
     * @return
     */
    /*@PostMapping("/selectUserList")
    @ApiOperation(value = "查询用户信息")
    @RequiresPermissions("system:user:list")
    public AjaxResult selectUserList() {
        List<SysUser> userList = userService.list(null);
        if (null != userList && userList.size() > 0) {
            return AjaxResult.ok().data("userList", userList);
        } else {
            return AjaxResult.error();
        }
    }*/
}
