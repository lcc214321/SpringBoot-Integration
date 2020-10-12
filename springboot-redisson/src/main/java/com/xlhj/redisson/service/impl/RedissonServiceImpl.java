package com.xlhj.redisson.service.impl;

import com.xlhj.redisson.service.RedissonService;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
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
public class RedissonServiceImpl implements RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public RLock getReadLock(String key) {
        RReadWriteLock readLock = redissonClient.getReadWriteLock(key);
        RLock rLock = readLock.readLock();
        return rLock;
    }

    @Override
    public RLock getWriteLock(String key) {
        RReadWriteLock writeLock = redissonClient.getReadWriteLock(key);
        RLock rLock = writeLock.writeLock();
        return rLock;
    }

    @Override
    public RSemaphore getSemaphore(String key) {
        RSemaphore semaphore = redissonClient.getSemaphore(key);
        return semaphore;
    }
}
