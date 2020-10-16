package com.xlhj.shiro.realm;

import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.exception.UserBlockedException;
import com.xlhj.shiro.exception.UserNotExistsException;
import com.xlhj.shiro.service.SysMenuService;
import com.xlhj.shiro.service.SysRoleService;
import com.xlhj.shiro.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //查询角色信息
        Set<String> roles = new HashSet<String>();
        //查询菜单权限信息
        Set<String> perms = new HashSet<String>();
        if (user.isAdmin()) {
            authorizationInfo.addRole("admin");
            authorizationInfo.addStringPermission("*:*:*");
        } else {
            roles = roleService.selectRoleCodesByUserId(user.getId());
            perms = menuService.selectPermsByUserId(user.getId());
            authorizationInfo.setRoles(roles);
            authorizationInfo.setStringPermissions(perms);
        }
        return authorizationInfo;
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
        SysUser user = null;
        try {
            user = userService.login(username);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            logger.warn("验证用户[" + username + "]未通过!");
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return authenticationInfo;
    }

    public static void main(String[] args) {
        SimpleHash simpleHash = new SimpleHash("MD5", "123456", "111111", 2);
        String encrypt = simpleHash.toString();
        System.out.println(encrypt);
    }
}
