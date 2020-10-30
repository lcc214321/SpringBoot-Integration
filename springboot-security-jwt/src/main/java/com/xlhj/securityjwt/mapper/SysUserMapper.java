package com.xlhj.securityjwt.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlhj.securityjwt.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lcj
 * @Date: 2020/10/29 15:58
 * @Description: 用户管理持久层接口
 * @Version: 0.0.1
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<Map<String, Object>> selectUserList(Long id);
}
