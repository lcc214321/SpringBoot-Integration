package com.xlhj.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.security.entity.SysMenu;

import java.util.Set;

/**
 * @ClassName SysMenuService
 * @Description 菜单权限管理业务接口
 * @Author liucaijing
 * @Date 2020/10/18 12:30
 * @Version 1.0
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID查询菜单权限
     * @param userId
     * @return
     */
    Set<String> selectPermsByUserId(Long userId);
}
