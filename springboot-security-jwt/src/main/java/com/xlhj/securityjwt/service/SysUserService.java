package com.xlhj.securityjwt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.securityjwt.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Author: lcj
 * @Date: 2020/10/23 17:43
 * @Description: 用户管理业务接口
 * @Version: 0.0.1
 */
public interface SysUserService extends IService<SysUser> {

    List<Map<String, Object>> selectUserList(Long id);
}
