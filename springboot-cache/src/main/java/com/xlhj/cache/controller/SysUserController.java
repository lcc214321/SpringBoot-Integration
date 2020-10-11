package com.xlhj.cache.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @Author liucaijing
 * @Date 2020/10/11 18:08
 * @Version 1.0
 */
public class SysUserController {

    @Cacheable(value = "user", key = "'user_key'", sync = true)
    public List selectUserList() {
        return null;
    }

    /**
     * 缓存失效模式使用
     * 同时进行多种缓存操作Caching
     * 存储同一类型的数据，都可以指定成同一个分区，分区名默认就是缓存的前缀
     *
     */
    //@CacheEvict(value = "user", key = "'user_key'")
    //@CacheEvict(value = "user", key = "#root.methodName")
    //@Caching(evict = {@CacheEvict(value = "user", key = "'user_key'"), @CacheEvict(value = "user", key = "#root.methodName")})
    @CacheEvict(value = "user", allEntries = true)//删除user分区内所有缓存
    @CachePut//双写模式
    public void updateUser() {

    }
}
