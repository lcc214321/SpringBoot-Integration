package com.xlhj.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.security.entity.SysMenu;
import com.xlhj.security.mapper.SysMenuMapper;
import com.xlhj.security.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @ClassName SysMenuServiceImpl
 * @Description 菜单权限管理业务接口实现类
 * @Author liucaijing
 * @Date 2020/10/18 12:36
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public Set<String> selectPermsByUserId(Long userId) {

        return null;
    }
}
