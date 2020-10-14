package com.xlhj.shiro.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.mapper.SysUserMapper;
import com.xlhj.shiro.service.SysUserService;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lcj
 * @Date: 2020/10/12 17:28
 * @Description: 用户管理业务接口实现类
 * @Version: 0.0.1
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public String register(SysUser user) {
        String message = "";
        String username = user.getUserName();
        String password = user.getPassword();
        if (StrUtil.isEmpty(username)) {
            message = "用户名不能为空!";
        } else if (StrUtil.isEmpty(password)) {
            message = "用户密码不能为空!";
        } else {
            boolean flag = userMapper.insert(user) > 0;
            if (flag) {
                message = "用户注册成功!";
            } else {
                message = "用户注册失败!";
            }
        }
        return message;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public SysUser login(String username, String password) {
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            throw new UnknownAccountException();
        }
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser user = userMapper.selectOne(wrapper);
        if (null == user) {
            throw new UnknownAccountException();
        }
        if (user.getStatus() == 20) {
            throw new LockedAccountException();
        }
        return user;
    }
}
