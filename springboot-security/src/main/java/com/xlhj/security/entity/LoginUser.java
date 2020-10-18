package com.xlhj.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName LoginUser
 * @Description 登录用户身份权限
 * @Author liucaijing
 * @Date 2020/10/18 12:12
 * @Version 1.0
 */
@Data
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = -7795533690633539258L;

    /**用户唯一标识*/
    private String token;

    /**登录时间*/
    private Long loginTime;

    /**过期时间*/
    private Long expireTime;

    /**权限列表*/
    private Set<String> permissions;

    /**用户信息*/
    private SysUser user;

    public LoginUser() {
    }

    public LoginUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 账号是否未过期，过期无法验证
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户是否解锁，锁定的用户无法进行身份验证
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 过期的密码不能认证
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 禁用的用户不能身份验证
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
