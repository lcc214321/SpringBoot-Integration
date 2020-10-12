package com.xlhj.cache.service.impl;

import com.xlhj.cache.service.CacheService;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName RedisServiceImpl
 * @Description redis业务接口实现类
 * @Author liucaijing
 * @Date 2020/10/9 23:32
 * @Version 1.0
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public RLock getReadLock(String key) {
        RReadWriteLock readLock = redissonClient.getReadWriteLock(key);
        RLock rLock = readLock.readLock();
        return rLock;
    }
}
