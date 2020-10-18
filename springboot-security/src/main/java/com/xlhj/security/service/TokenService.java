package com.xlhj.security.service;

import cn.hutool.core.util.StrUtil;
import com.xlhj.security.config.RedisConfig;
import com.xlhj.security.entity.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TokenService
 * @Description token验证处理类
 * @Author liucaijing
 * @Date 2020/10/18 13:59
 * @Version 1.0
 */
@Component
public class TokenService {

    @Autowired
    private RedisConfig redisConfig;

    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 获取用户身份
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StrUtil.isNotEmpty(token)) {
            Claims claims = Jwts.parser().setSigningKey("qazxswedcvfrtgbnhyujmkiolp").parseClaimsJws(token).getBody();
            String uuid = (String) claims.get("login_key");
            String userKey = "login_tokens:" + uuid;
            LoginUser user = redisConfig.getCacheObject(userKey);
            return user;
        }
        return null;
    }

    /**
     * 删除用户身份信息
     * @param token
     */
    public void deleteLoginUser(String token) {
        if (StrUtil.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisConfig.deleteCacheObject(userKey);
        }
    }

    /**
     * 获取token的key
     * @param key
     * @return
     */
    private String getTokenKey(String key) {
        return "login_tokens:" + key;
    }

    /**
     * 验证token有效期
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新token
     * @param loginUser
     */
    private void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + 30 * MILLIS_MINUTE);
        String userKey = getTokenKey(loginUser.getToken());
        redisConfig.setCacheObject(userKey, loginUser, 30, TimeUnit.MINUTES);
    }
}
