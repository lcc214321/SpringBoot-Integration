package com.xlhj.oss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlhj.oss.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysUserMapper
 * @Description 用户信息持久层接口
 * @Author liucaijing
 * @Date 2020/11/18 21:14
 * @Version 1.0
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
}
