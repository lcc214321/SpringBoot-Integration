package com.xlhj.fastdfs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.fastdfs.entity.SysUser;
import com.xlhj.fastdfs.mapper.SysUserMapper;
import com.xlhj.fastdfs.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserServiceImpl
 * @Description 用户信息业务接口实现类
 * @Author liucaijing
 * @Date 2020/11/18 21:14
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据用户ID修改用户头像路径
     * @param userId
     * @param avatar
     * @return
     */
    @Override
    public int updateAvatarById(Long userId, String avatar) {
        SysUser user = new SysUser();
        user.setId(userId);
        user.setAvatar(avatar);
        int num = userMapper.updateById(user);
        return num;
    }
}
