package com.xlhj.redisson.service;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;

/**
 * @ClassName RedissonService
 * @Description redis业务接口
 * @Author liucaijing
 * @Date 2020/10/9 23:32
 * @Version 1.0
 */
public interface RedissonService {

    /**
     * 获取锁
     * @param key
     * @return
     */
    RLock getLock(String key);

    /**
     * 获取读锁
     * @param key
     * @return
     */
    RLock getReadLock(String key);

    /**
     * 获取写锁
     * @param key
     * @return
     */
    RLock getWriteLock(String key);

    /**
     * 获取信号量
     * @param key
     * @return
     */
    RSemaphore getSemaphore(String key);
}
