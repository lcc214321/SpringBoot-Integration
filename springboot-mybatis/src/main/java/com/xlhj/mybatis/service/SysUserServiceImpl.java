package com.xlhj.mybatis.service;

import com.xlhj.mybatis.entity.SysUser;
import com.xlhj.mybatis.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户管理Service接口实现类
 * @Author liucaijing
 * @Date 2020/10/10 23:07
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public SysUser selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int updateUser(SysUser user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int insertUser(SysUser user) {
        return userMapper.insertUser(user);
    }
}
