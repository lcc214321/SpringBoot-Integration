package com.xlhj.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlhj.shiro.entity.SysMenu;

import java.util.List;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:20
 * @Description: 菜单管理Mapper接口
 * @Version: 0.0.1
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermsByUserId(Long userId);

}
