package com.xlhj.ehcache.service.impl;

import com.xlhj.ehcache.service.EhcacheService;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: lcj
 * @Date: 2020/10/9 15:21
 * @Description: 缓存业务接口实现类
 * @Version: 0.0.1
 */
@Service
public class EhcacheServiceImpl implements EhcacheService {

    @Resource(name = "ehcacheManager")
    private CacheManager cacheManager;

    @Override
    public void putCache(String cacheName, String key, String value) {
        Cache<String, String> cache = cacheManager.getCache(cacheName, String.class, String.class);
        cache.put(key, value);
    }

    @Override
    public String getCache(String cacheName, String key) {
        Cache<String, String> cache = cacheManager.getCache(cacheName, String.class, String.class);
        String cacheValue = cache.get(key);
        return cacheValue;
    }

    @Override
    public void clearCache(String cacheName) {
        Cache<String, String> cache = cacheManager.getCache(cacheName, String.class, String.class);
        cache.clear();
    }

    @Override
    public void removeCache(String cacheName, String key) {
        Cache<String, String> cache = cacheManager.getCache(cacheName, String.class, String.class);
        cache.remove(key);
    }

    @Override
    public boolean removeCache(String cacheName, String key, String value) {
        Cache<String, String> cache = cacheManager.getCache(cacheName, String.class, String.class);
        boolean flag = cache.remove(key, value);
        return flag;
    }
}
