package com.xlhj.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.shiro.entity.SysRole;

import java.util.Set;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:20
 * @Description: 角色管理业务接口
 * @Version: 0.0.1
 */
public interface SysRoleService extends IService<SysRole> {

    Set<String> selectRoleCodes(Long id);
}
