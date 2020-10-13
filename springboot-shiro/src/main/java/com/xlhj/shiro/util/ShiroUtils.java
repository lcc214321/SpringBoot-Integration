package com.xlhj.shiro.util;

import cn.hutool.core.util.StrUtil;
import com.xlhj.shiro.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

/**
 * @Author: lcj
 * @Date: 2020/10/13 9:43
 * @Description: shiro工具类
 * @Version: 0.0.1
 */
public class ShiroUtils {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取用户信息
     * @return
     */
    public static SysUser getSysUser() {
        SysUser user = new SysUser();
        Object object = getSubject().getPrincipal();
        if (StringUtils.isNotNull(object)) {
            user = new SysUser();
            BeanUtils.copyProperties(user, object);
        }
        return user;
    }
}
