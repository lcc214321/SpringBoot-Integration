package com.xlhj.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlhj.security.entity.SysMenu;
import com.xlhj.security.entity.SysRole;
import com.xlhj.security.entity.SysUser;
import com.xlhj.security.mapper.SysMenuMapper;
import com.xlhj.security.mapper.SysRoleMapper;
import com.xlhj.security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: lcj
 * @Date: 2020/10/26 16:28
 * @Description: TODO
 * @Version: 0.0.1
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser currentUser = userMapper.selectOne(wrapper);
        if (currentUser == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        System.out.println(currentUser);
        //获取用户角色和菜单权限
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<SysRole> roleList = roleMapper.selectRoleCodesByUserId(currentUser.getId());
        for (SysRole role : roleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRoleCode());
            authorityList.add(authority);
        }
        List<SysMenu> permsList = menuMapper.selectMenuPermsByUserId(currentUser.getId());
        for (SysMenu perm : permsList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(perm.getPerms());
            authorityList.add(authority);
        }

        //List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authStr);
        return new User(currentUser.getUserName(), currentUser.getPassword(), true, true, true, true, authorityList);
    }
}
