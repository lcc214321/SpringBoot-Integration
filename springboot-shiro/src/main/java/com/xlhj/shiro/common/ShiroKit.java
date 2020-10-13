package com.xlhj.shiro.common;

import com.xlhj.shiro.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Author: lcj
 * @Date: 2020/10/13 16:43
 * @Description: Shiro工具类
 * @Version: 0.0.1
 */
public class ShiroKit {

    /**
     * 获取当前subject
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前用户
     * @return
     */
    public static SysUser getUser() {
        return (SysUser) getSubject().getPrincipals().getPrimaryPrincipal();
    }
}
