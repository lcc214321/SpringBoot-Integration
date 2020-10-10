package com.xlhj.redis.service;

import com.xlhj.redis.entity.SysUser;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisService
 * @Description redis业务接口
 * @Author liucaijing
 * @Date 2020/10/9 23:32
 * @Version 1.0
 */
public interface RedisService {

    /**
     * 删除redis中key
     * @param key
     */
    void delete(String key);

    /**
     * redis中是否存在key
     * @param key
     * @return
     */
    boolean existsKey(String key);

    /**
     * 设置key过期时间
     * @param key
     * @param time
     * @param timeUnit
     */
    void expireKey(String key, long time, TimeUnit timeUnit);

    /**
     * 保存带过期时间的String类型数据
     * @param key
     * @param value
     * @param times
     */
    void set(String key, Object value, long times);

    /**
     * 获取String类型数据
     * @param key
     */
    SysUser get(String key);

    /**
     * 获取List类型数据
     * @param key
     * @return
     */
    List<SysUser> leftPop(String key);

    /**
     * 添加List类型数据
     * @param key
     * @param userList
     * @param times
     * @return
     */
    Long rightPush(String key, List<SysUser> userList, long times);
}
