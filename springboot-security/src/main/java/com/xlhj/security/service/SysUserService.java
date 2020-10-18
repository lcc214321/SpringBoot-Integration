package com.xlhj.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.security.entity.SysUser;

/**
 * @ClassName SysUserService
 * @Description 用户管理业务接口
 * @Author liucaijing
 * @Date 2020/10/18 11:55
 * @Version 1.0
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser selectUserByUserName(String username);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);
}
