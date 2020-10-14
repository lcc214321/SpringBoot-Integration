package com.xlhj.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.shiro.entity.SysMenu;
import com.xlhj.shiro.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:22
 * @Description: 菜单管理业务接口
 * @Version: 0.0.1
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户信息查询菜单权限
     * @param user
     * @return
     */
    List<SysMenu> selectMenusByUser(SysUser user);

    /**
     * 根据用户ID查询菜单权限
     * @param userId
     * @return
     */
    Set<String> selectPermsByUserId(Long userId);
}
