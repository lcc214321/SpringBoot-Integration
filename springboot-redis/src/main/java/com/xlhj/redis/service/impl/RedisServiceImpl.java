package com.xlhj.redis.service.impl;

import com.xlhj.redis.entity.SysUser;
import com.xlhj.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private ValueOperations<String, Object> valueOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    @Override
    public void set(String key, Object value, long times) {
        valueOperations.set(key, value, times, TimeUnit.SECONDS);
    }

    @Override
    public SysUser get(String key) {
        return (SysUser) valueOperations.get(key);
    }

    @Override
    public List<SysUser> leftPop(String key) {
        List<SysUser> userList = (List<SysUser>) listOperations.leftPop(key);
        return userList;
    }

    @Override
    public Long rightPush(String key, List<SysUser> userList, long times) {
        Long num = listOperations.rightPush("userList", userList);
        if (times >0) {
            expireKey(key, times, TimeUnit.SECONDS);
        }
        return num;
    }


}
