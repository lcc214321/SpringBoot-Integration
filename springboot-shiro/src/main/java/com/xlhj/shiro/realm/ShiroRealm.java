package com.xlhj.shiro.realm;

import com.xlhj.shiro.entity.SysUser;
import com.xlhj.shiro.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String loginName = token.getUsername();
        String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }
        try {
            SysUser user = userService.login(loginName, password);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("登录验证用户[" + loginName + "]未通过{}", e.getMessage());
        }

        return null;
    }
}
