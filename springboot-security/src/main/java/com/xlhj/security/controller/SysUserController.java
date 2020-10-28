package com.xlhj.security.controller;

import com.xlhj.security.common.ResultCode;
import com.xlhj.security.common.ResultData;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lcj
 * @Date: 2020/10/27 9:46
 * @Description: TODO
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:list')")
    public ResultData userList() {
        return ResultData.ok(ResultCode.SUCCESS, "访问用户查询界面成功!");
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('user:add')")
    public ResultData userAdd() {
        return ResultData.ok(ResultCode.SUCCESS, "访问用户新增界面成功!");
    }

    /**
     * 测试无权限访问，数据库中权限是user:update
     * @return
     */
    @GetMapping("/update")
    @PreAuthorize("hasAuthority('user:edit')")
    public ResultData userUpdate() {
        return ResultData.ok(ResultCode.SUCCESS, "访问用户修改界面成功!");
    }

    @GetMapping("/delete")
    @Secured("ROLE_admin")
    public ResultData userDelete() {
        return ResultData.ok(ResultCode.SUCCESS, "访问用户删除界面成功!");
    }
}
