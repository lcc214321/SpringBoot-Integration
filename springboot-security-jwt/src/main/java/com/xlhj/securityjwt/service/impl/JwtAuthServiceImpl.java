package com.xlhj.securityjwt.service.impl;

import com.xlhj.securityjwt.entity.SysUser;
import com.xlhj.securityjwt.service.JwtAuthService;
import com.xlhj.securityjwt.util.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: lcj
 * @Date: 2020/10/29 8:43
 * @Description: Jwt权限业务接口实现类
 * @Version: 0.0.1
 */
@Service
public class JwtAuthServiceImpl implements JwtAuthService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtTokenUtils jwtTokenUtils;

    /**
     * 登录认证换取JWT令牌
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new RuntimeException("用户名或密码不正确!");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtTokenUtils.generateToken(userDetails);
    }
}
