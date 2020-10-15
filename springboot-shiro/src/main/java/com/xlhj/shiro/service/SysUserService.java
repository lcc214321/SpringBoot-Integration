package com.xlhj.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.shiro.entity.SysUser;

/**
 * @Author: lcj
 * @Date: 2020/10/12 17:28
 * @Description: 用户管理业务接口
 * @Version: 0.0.1
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户登录
     * @param username
     * @return
     */
    SysUser login(String username);
}
