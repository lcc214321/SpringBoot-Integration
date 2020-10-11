package com.xlhj.mybatis.controller;

import com.xlhj.mybatis.common.AjaxResult;
import com.xlhj.mybatis.entity.SysUser;
import com.xlhj.mybatis.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysUserController
 * @Description 用户管理Controller
 * @Author liucaijing
 * @Date 2020/10/10 23:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/mybatis/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping("/selectUserList")
    @ApiOperation(value = "查询用户列表")
    public AjaxResult selectUserList() {
        List<SysUser> userList = userService.selectUserList();
        return AjaxResult.ok().data("userList", userList);
    }

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/selectUserById/{id}")
    @ApiOperation(value = "根据用户ID查询用户信息")
    public AjaxResult selectUserById(@PathVariable Long id) {
        SysUser user = userService.selectUserById(id);
        return AjaxResult.ok().data("user", user);
    }

    /**
     * 根据用户ID删除用户信息
     * @param id
     * @return
     */
    @GetMapping("/deleteUserById/{id}")
    @ApiOperation(value = "根据用户ID删除用户信息")
    public AjaxResult deleteUserById(@PathVariable Long id) {
        int num = userService.deleteUserById(id);
        return AjaxResult.ok();
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    @ApiOperation(value = "更新用户信息")
    public AjaxResult updateUser(@RequestBody SysUser user) {
        int num = userService.updateUser(user);
        return AjaxResult.ok();
    }

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    @PostMapping("/insertUser")
    @ApiOperation(value = "新增用户信息")
    public AjaxResult insertUser(@RequestBody SysUser user) {
        int num = userService.insertUser(user);
        return AjaxResult.ok();
    }
}
