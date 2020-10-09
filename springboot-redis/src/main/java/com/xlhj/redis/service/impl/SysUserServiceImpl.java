package com.xlhj.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.redis.entity.SysUser;
import com.xlhj.redis.mapper.SysUserMapper;
import com.xlhj.redis.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户管理业务接口实现类
 * @Author liucaijing
 * @Date 2020/10/9 23:29
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
