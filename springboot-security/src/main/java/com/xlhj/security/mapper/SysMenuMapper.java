package com.xlhj.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlhj.security.entity.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lcj
 * @Date: 2020/10/27 9:20
 * @Description: 菜单权限管理持久层接口
 * @Version: 0.0.1
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户ID查询菜单权限
     * @param userId
     * @return
     */
    List<SysMenu> selectMenuPermsByUserId(Long userId);
}
