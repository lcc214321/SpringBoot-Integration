package com.xlhj.securityjwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.securityjwt.entity.SysUser;
import com.xlhj.securityjwt.mapper.SysUserMapper;
import com.xlhj.securityjwt.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: lcj
 * @Date: 2020/10/23 17:44
 * @Description: 用户管理业务接口实现类
 * @Version: 0.0.1
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<Map<String, Object>> selectUserList(Long id) {
        List<Map<String, Object>> mapList = userMapper.selectUserList(id);
        return mapList;
    }
}
