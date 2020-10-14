package com.xlhj.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlhj.shiro.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: lcj
 * @Date: 2020/10/13 10:19
 * @Description: 角色管理Mapper接口
 * @Version: 0.0.1
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户ID查询角色信息
     * @param userId
     * @return
     */
    List<SysRole> selectRoleCodesByUserId(Long userId);
}
