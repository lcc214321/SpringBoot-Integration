package com.xlhj.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.shiro.entity.SysMenu;

import java.util.Set;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:22
 * @Description: 菜单管理业务接口
 * @Version: 0.0.1
 */
public interface SysMenuService extends IService<SysMenu> {

    Set<String> selectPermsByUserId(Long id);
}
