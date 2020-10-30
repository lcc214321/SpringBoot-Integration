package com.xlhj.securityjwt.service;

/**
 * @Author: lcj
 * @Date: 2020/10/29 8:43
 * @Description: Jwt权限业务接口
 * @Version: 0.0.1
 */
public interface JwtAuthService {

    /**
     * 登录认证获取JWT令牌
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);
}
