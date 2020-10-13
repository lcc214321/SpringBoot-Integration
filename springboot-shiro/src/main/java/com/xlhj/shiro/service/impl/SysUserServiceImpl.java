package com.xlhj.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.mapper.SysUserMapper;
import com.xlhj.shiro.service.SysUserService;
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
     * 根据登录名和密码查询用户信息
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public SysUser selectUserByLoginNameAndPassword(String loginName, String password) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        wrapper.eq("login_name", loginName);
        wrapper.eq("password", password);
        SysUser user = userMapper.selectOne(wrapper);
        return user;
    }
}
