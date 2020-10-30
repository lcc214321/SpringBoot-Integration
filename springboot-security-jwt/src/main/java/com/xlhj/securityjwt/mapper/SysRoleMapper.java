package com.xlhj.securityjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlhj.securityjwt.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lcj
 * @Date: 2020/10/29 16:04
 * @Description: 角色管理持久层接口
 * @Version: 0.0.1
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    List<SysRole> selectRoleCodesByUserId(Long userId);
}
