package com.xlhj.oss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.oss.entity.SysUser;
import com.xlhj.oss.mapper.SysUserMapper;
import com.xlhj.oss.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户信息业务接口实现类
 * @Author liucaijing
 * @Date 2020/11/19 20:57
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据用户ID更新用户头像路径
     * @param userId
     * @param avatar
     */
    @Override
    public int updateAvatarByUserId(Long userId, String avatar) {
        SysUser user = new SysUser();
        user.setId(userId);
        user.setAvatar(avatar);
        int num = userMapper.updateById(user);
        return num;
    }
}
