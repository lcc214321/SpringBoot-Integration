package com.xlhj.security.service;

import com.xlhj.security.entity.LoginUser;
import com.xlhj.security.entity.SysUser;
import com.xlhj.security.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @ClassName UserDetailsService
 * @Description 用户验证处理
 * @Author liucaijing
 * @Date 2020/10/18 11:52
 * @Version 1.0
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysMenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (user == null) {
            logger.info("用户：{}不存在!", username);
            throw new UserException("对不起，您输入的账号：" + username + "不存在!");
        }
        if (user.getStatus() == 20) {
            logger.info("用户：{}已停用!", username);
            throw new UserException("对不起，您的账号：" + username + "已停用!");
        }
        //获取菜单权限
        Set<String> perms = menuService.selectPermsByUserId(user.getId());
        return new LoginUser(user, perms);
    }
}
