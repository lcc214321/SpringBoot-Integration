package com.xlhj.security.config;

import com.xlhj.security.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisConfig
 * @Description redis配置类
 * @Author liucaijing
 * @Date 2020/10/18 14:17
 * @Version 1.0
 */
@Component
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取缓存
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 删除缓存
     * @param key
     */
    public boolean deleteCacheObject(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 新增缓存
     * @param key
     * @param user
     * @param timeout
     * @param minutes
     */
    public void setCacheObject(String key, LoginUser user, int timeout, TimeUnit minutes) {
        redisTemplate.opsForValue().set(key, user, timeout, minutes);
    }
}
