package com.xlhj.mybatis.service;

import com.xlhj.mybatis.entity.SysUser;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Description 用户管理Service接口
 * @Author liucaijing
 * @Date 2020/10/10 23:06
 * @Version 1.0
 */
public interface SysUserService {

    /**
     * 查询用户信息列表
     * @return
     */
    List<SysUser> selectUserList();

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    SysUser selectUserById(Long id);

    /**
     * 根据用户ID删除用户信息
     * @param id
     * @return
     */
    int deleteUserById(Long id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(SysUser user);

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    int insertUser(SysUser user);
}
