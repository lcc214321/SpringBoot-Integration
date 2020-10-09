package com.xlhj.ehcache.service;

/**
 * @Author: lcj
 * @Date: 2020/10/9 15:19
 * @Description: 缓存业务接口
 * @Version: 0.0.1
 */
public interface EhcacheService {

    /**
     * 给缓存中添加值
     * @param cacheName
     * @param key
     * @param value
     */
    void putCache(String cacheName, String key, String value);

    /**
     * 获取缓存的的值
     * @param cacheName
     * @param key
     * @return
     */
    String getCache(String cacheName, String key);

    /**
     * 清理缓存
     * @param cacheName
     */
    void clearCache(String cacheName);

    /**
     * 根据key删除缓存中的值
     * @param cacheName
     * @param key
     */
    void removeCache(String cacheName, String key);

    /**
     * 根据key和value删除缓存中的值
     * @param cacheName
     * @param key
     * @param value
     * @return
     */
    boolean removeCache(String cacheName, String key, String value);
}
