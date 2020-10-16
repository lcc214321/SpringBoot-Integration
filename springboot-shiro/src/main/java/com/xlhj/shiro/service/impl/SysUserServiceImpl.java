package com.xlhj.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.exception.UserBlockedException;
import com.xlhj.shiro.exception.UserNotExistsException;
import com.xlhj.shiro.mapper.SysUserMapper;
import com.xlhj.shiro.service.SysUserService;
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
     * 用户登录
     * @param username
     * @return
     */
    @Override
    public SysUser login(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UserNotExistsException();
        }
        if (user.getStatus() == 20) {
            throw new UserBlockedException();
        }
        return user;
    }
}
