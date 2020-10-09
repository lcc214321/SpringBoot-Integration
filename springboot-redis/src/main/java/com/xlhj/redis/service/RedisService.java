package com.xlhj.redis.service;

/**
 * @ClassName RedisService
 * @Description redis业务接口
 * @Author liucaijing
 * @Date 2020/10/9 23:32
 * @Version 1.0
 */
public interface RedisService {

    /**
     * 保存String类型数据
     * @param key
     * @param value
     */
    void setStringData(String key, String value);

    /**
     * 获取String类型数据
     * @param key
     */
    void getStringData(String key);
}
