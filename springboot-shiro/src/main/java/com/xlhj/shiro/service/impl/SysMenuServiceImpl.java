package com.xlhj.shiro.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.shiro.entity.SysMenu;
import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.mapper.SysMenuMapper;
import com.xlhj.shiro.mapper.SysRoleMenuMapper;
import com.xlhj.shiro.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:24
 * @Description: 菜单管理业务接口实现类
 * @Version: 0.0.1
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public List<SysMenu> selectMenusByUser(SysUser user) {
        List<SysMenu> menuList = new LinkedList<SysMenu>();
        if (user.isAdmin()) {
            menuList = menuMapper.selectList(null);
        } else {
            menuList = menuMapper.selectMenusByUserId(user.getId());
        }
        return getChildPerms(menuList, 0);
    }

    /**
     * 根据用户ID查询菜单权限
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<String>();
        for (String perm : perms) {
            if (StrUtil.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }


    /**
     * 根据父级ID查询所有子类
     * @param menuList
     * @param parentId
     * @return
     */
    private List<SysMenu> getChildPerms(List<SysMenu> menuList, int parentId) {
        List<SysMenu> resultList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = menuList.iterator(); iterator.hasNext();) {
            SysMenu menu = iterator.next();
            if (menu.getParentId() == parentId) {
                recursionList(menuList, menu);
                resultList.add(menu);
            }
        }
        return resultList;
    }

    /**
     * 递归查询菜单列表
     * @param menuList
     * @param menu
     */
    private void recursionList(List<SysMenu> menuList, SysMenu menu) {
        List<SysMenu> childList = getChildList(menuList, menu);
        menu.setChildren(childList);
        for (SysMenu child : childList) {
            if (hasChild(menuList, child)) {
                recursionList(menuList, child);
            }
        }
    }

    /**
     * 判断是否有子节点
     * @param menuList
     * @param child
     * @return
     */
    private boolean hasChild(List<SysMenu> menuList, SysMenu child) {
        return getChildList(menuList, child).size() > 0 ? true : false;
    }

    /**
     * 获取子节点列表
     * @param menuList
     * @param child
     * @return
     */
    private List<SysMenu> getChildList(List<SysMenu> menuList, SysMenu child) {
        List<SysMenu> childList = new ArrayList<SysMenu>();
        Iterator<SysMenu> iterator = menuList.iterator();
        while (iterator.hasNext()) {
            SysMenu me = (SysMenu) iterator.next();
            if (me.getParentId().longValue() == child.getId().longValue()) {
                childList.add(me);
            }
        }
        return childList;
    }
}
