package com.xlhj.plus.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xlhj.plus.common.AjaxResult;
import com.xlhj.plus.common.Md5Utils;
import com.xlhj.plus.entity.SysUser;
import com.xlhj.plus.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lcj
 * @since 2020-10-01
 */
@RestController
@RequestMapping("/plus/user")
public class SysUserController {

    private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService userService;

    /**
     * 查询所有用户列表
     * @return
     */
    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有用户列表")
    public AjaxResult findAllUser() {
        List<SysUser> userList = userService.list(null);
        return AjaxResult.ok().data("items", userList);
    }

    /**
     * 分页查询用户信息
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/pageListUser/{current}/{limit}")
    @ApiOperation(value = "分页查询用户信息")
    public AjaxResult pageListUser(@PathVariable long current, @PathVariable long limit) {
        Page<SysUser> userPage = new Page<SysUser>(current, limit);
        userService.page(userPage, null);
        long total = userPage.getTotal();
        List<SysUser> records = userPage.getRecords();
        return AjaxResult.ok().data("total", total).data("rows", records);
    }

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增用户")
    public AjaxResult add(@RequestBody SysUser user) {
        user.setPassword(Md5Utils.toHex(Md5Utils.md5(user.getPassword())));
        boolean flag = userService.save(user);
        if (flag) {
            return AjaxResult.ok();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @GetMapping("getUserById/{id}")
    @ApiOperation(value = "根据ID查询用户信息")
    public AjaxResult getUserById(@PathVariable Long id) {
        SysUser user = userService.getById(id);
        return AjaxResult.ok().data("user", user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改用户")
    public AjaxResult update(@RequestBody SysUser user) {
        userService.updateById(user);
        return AjaxResult.ok();
    }

    /**
     * 逻辑删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "逻辑删除用户信息")
    public AjaxResult remove(@ApiParam(name = "id", value = "主键ID", required = true) @PathVariable Long id) {
        boolean flag = userService.removeById(id);
        if (flag) {
            return AjaxResult.ok();
        } else {
            return AjaxResult.error();
        }
    }
}

