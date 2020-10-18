package com.xlhj.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.security.entity.SysRole;
import com.xlhj.security.mapper.SysRoleMapper;
import com.xlhj.security.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysRoleServiceImpl
 * @Description 角色管理业务接口实现类
 * @Author liucaijing
 * @Date 2020/10/18 12:33
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
