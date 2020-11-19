package com.xlhj.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.oss.entity.SysUser;

/**
 * @ClassName SysUserService
 * @Description 用户信息业务接口
 * @Author liucaijing
 * @Date 2020/11/19 20:56
 * @Version 1.0
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户ID更新用户头像路径
     * @param userId
     * @param avatar
     */
    int updateAvatarByUserId(Long userId, String avatar);
}
