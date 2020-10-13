package com.xlhj.shiro.service.impl;

import cn.hutool.core.util.StrUtil;
import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.exception.UserNotExistsException;
import com.xlhj.shiro.service.SysLoginService;
import com.xlhj.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:30
 * @Description: 登录业务接口实现类
 * @Version: 0.0.1
 */
@Service
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private SysUserService userService;

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public SysUser login(String loginName, String password) {
        //校验用户名和密码
        if (StrUtil.isEmpty(loginName) || StrUtil.isEmpty(password)) {
            throw new UserNotExistsException();
        }
        SysUser user = userService.selectUserByLoginNameAndPassword(loginName, password);
        if (user == null) {
            throw new UserNotExistsException();
        }
        return user;
    }
}
