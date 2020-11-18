package com.xlhj.fastdfs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.fastdfs.entity.SysUser;

/**
 * @ClassName SysUserService
 * @Description 用户信息业务接口
 * @Author liucaijing
 * @Date 2020/11/18 21:14
 * @Version 1.0
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户ID修改用户头像路径
     * @param userId
     * @param avatar
     * @return
     */
    int updateAvatarById(Long userId, String avatar);
}
