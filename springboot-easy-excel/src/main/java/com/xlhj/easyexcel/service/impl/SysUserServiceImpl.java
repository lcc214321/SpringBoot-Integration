package com.xlhj.easyexcel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.easyexcel.entity.SysUser;
import com.xlhj.easyexcel.mapper.SysUserMapper;
import com.xlhj.easyexcel.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户信息业务接口实现类
 * @Author liucaijing
 * @Date 2020/11/8 14:30
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
