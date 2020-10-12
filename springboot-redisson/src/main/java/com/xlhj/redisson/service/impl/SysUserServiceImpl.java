package com.xlhj.redisson.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.redisson.entity.SysUser;
import com.xlhj.redisson.mapper.SysUserMapper;
import com.xlhj.redisson.service.SysUserService;
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
