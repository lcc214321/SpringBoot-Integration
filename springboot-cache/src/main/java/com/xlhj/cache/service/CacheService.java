package com.xlhj.cache.service;

import org.redisson.api.RLock;

/**
 * @ClassName CacheService
 * @Description redis业务接口
 * @Author liucaijing
 * @Date 2020/10/9 23:32
 * @Version 1.0
 */
public interface CacheService {

    /**
     * 获取读锁
     * @param key
     * @return
     */
    RLock getReadLock(String key);
}
