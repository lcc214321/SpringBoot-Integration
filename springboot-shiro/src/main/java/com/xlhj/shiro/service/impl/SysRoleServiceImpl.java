package com.xlhj.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.shiro.entity.SysRole;
import com.xlhj.shiro.mapper.SysRoleMapper;
import com.xlhj.shiro.mapper.SysRoleMenuMapper;
import com.xlhj.shiro.mapper.SysUserRoleMapper;
import com.xlhj.shiro.service.SysRoleService;
import com.xlhj.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:23
 * @Description: 角色管理业务接口实现类
 * @Version: 0.0.1
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    /**
     * 根据用户ID查询角色信息
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectRoleCodesByUserId(Long userId) {
        List<SysRole> roleList = roleMapper.selectRoleCodesByUserId(userId);
        Set<String> roleSet = new HashSet<>();
        for (SysRole role : roleList) {
            if (null != role) {
                roleSet.addAll(Arrays.asList(role.getRoleCode().trim().split(",")));
            }
        }
        return roleSet;
    }
}
