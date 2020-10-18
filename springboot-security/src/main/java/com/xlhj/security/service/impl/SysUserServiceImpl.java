package com.xlhj.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.security.entity.LoginUser;
import com.xlhj.security.entity.SysUser;
import com.xlhj.security.mapper.SysUserMapper;
import com.xlhj.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户管理业务接口实现类
 * @Author liucaijing
 * @Date 2020/10/18 11:55
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public SysUser selectUserByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser user = userMapper.selectOne(wrapper);
        return user;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return null;
    }
}
