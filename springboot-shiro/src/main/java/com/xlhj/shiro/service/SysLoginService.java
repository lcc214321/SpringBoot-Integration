package com.xlhj.shiro.service;

import com.xlhj.shiro.entity.SysUser;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:30
 * @Description: 登录业务接口
 * @Version: 0.0.1
 */
public interface SysLoginService {

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    SysUser login(String loginName, String password);
}
