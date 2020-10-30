package com.xlhj.securityjwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlhj.securityjwt.entity.SysRole;
import com.xlhj.securityjwt.entity.SysUser;
import com.xlhj.securityjwt.mapper.SysRoleMapper;
import com.xlhj.securityjwt.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lcj
 * @Date: 2020/10/29 15:57
 * @Description: TODO
 * @Version: 0.0.1
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户：" + username + "不存在!");
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<SysRole> roleList = roleMapper.selectRoleCodesByUserId(user.getId());
        for (SysRole role : roleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRoleCode());
            authorityList.add(authority);
        }
        return new User(user.getUserName(), user.getPassword(), authorityList);
    }
}
