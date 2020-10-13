package com.xlhj.shiro.realm;

import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.service.SysLoginService;
import com.xlhj.shiro.service.SysMenuService;
import com.xlhj.shiro.service.SysRoleService;
import com.xlhj.shiro.service.SysUserService;
import com.xlhj.shiro.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lcj
 * @Date: 2020/10/12 17:26
 * @Description: 自定义Realm
 * @Version: 0.0.1
 */
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysLoginService loginService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        Set<String> menus = menuService.selectPermsByUserId(user.getId());
        simpleAuthorizationInfo.setStringPermissions(menus);
        return simpleAuthorizationInfo;
        /*SysUser user = ShiroUtils.getSysUser();
        Set<String> roles = new HashSet<String>();
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            roles = roleService.selectRoleCodes(user.getId());
            menus = menuService.selectPermsByUserId(user.getId());
            info.setRoles(roles);
            info.setStringPermissions(menus);
        }
        return info;*/
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        SysUser user = userService.selectUserByLoginNameAndPassword(userToken.getUsername(), new String(userToken.getPassword()));
        if (null == user) {
            throw new UnknownAccountException();
        }
        SecurityUtils.getSubject().getSession().setAttribute("currentLoginedUser", user);
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");

        /*UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String loginName = token.getUsername();
        String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }
        SysUser user = new SysUser();
        try {
            user = loginService.login(loginName, password);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("登录验证用户[" + loginName + "]未通过{}", e.getMessage());
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;*/
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
