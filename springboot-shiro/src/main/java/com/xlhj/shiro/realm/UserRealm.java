package com.xlhj.shiro.realm;

import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.service.SysMenuService;
import com.xlhj.shiro.service.SysRoleService;
import com.xlhj.shiro.service.SysUserService;
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
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysMenuService menuService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("Shiro授权....");
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //查询角色信息
        Set<String> roles = new HashSet<String>();
        //查询菜单权限信息
        Set<String> perms = new HashSet<String>();
        roles = roleService.selectRoleCodesByUserId(user.getId());
        perms = menuService.selectPermsByUserId(user.getId());
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("Shiro认证...");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        String username = userToken.getUsername();
        String password = new String(userToken.getPassword());
        SysUser user = new SysUser();
        try {
            user = userService.login(username, password);
        } catch (UnknownAccountException e) {//未查询到该账户
            throw new UnknownAccountException(e.getMessage());
        } catch (IncorrectCredentialsException e) {//密码不正确
            throw new IncorrectCredentialsException(e.getMessage());
        } catch (ExcessiveAttemptsException e) {//登录失败次数过多
            throw new ExcessiveAttemptsException(e.getMessage());
        } catch (LockedAccountException e) {//账号被锁定
            throw new LockedAccountException(e.getMessage());
        } catch (DisabledAccountException e) {//账号被禁用
            throw new DisabledAccountException(e.getMessage());
        } catch (Exception e) {
            logger.info("用户[" + username + "]登录验证未通过{}", e.getMessage());
            throw new AuthenticationException((e.getMessage()));
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
