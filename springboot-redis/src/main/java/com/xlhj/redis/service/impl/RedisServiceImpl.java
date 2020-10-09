package com.xlhj.redis.service.impl;

import com.xlhj.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @ClassName RedisServiceImpl
 * @Description redis业务接口实现类
 * @Author liucaijing
 * @Date 2020/10/9 23:32
 * @Version 1.0
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void setStringData(String key, String value) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    @Override
    public void getStringData(String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.get(key);
    }
}
