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
     * 根据登录名和密码查询用户信息
     * @param loginName
     * @param password
     * @return
     */
    SysUser selectUserByLoginNameAndPassword(String loginName, String password);
}
