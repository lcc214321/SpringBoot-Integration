package com.xlhj.cache.controller;

import com.xlhj.cache.common.AjaxResult;
import com.xlhj.cache.entity.SysUser;
import com.xlhj.cache.service.CacheService;
import com.xlhj.cache.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SysUserController
 * @Description 用户管理(SpringBoot整合Redisson和SpringCache实现分布式缓存和分布式锁)
 * @Author liucaijing
 * @Date 2020/10/11 18:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/cache/user")
public class SysUserController {

    private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private CacheService cacheService;
    @Autowired
    private SysUserService userService;

    /**
     * 根据用户ID查询用户信息(实现分布式缓存和分布式锁)
     * @param id
     * @return
     */
    @GetMapping("/selectUserById/{id}")
    @ApiOperation(value = "根据用户ID查询用户信息")
    @Cacheable(value = "user", key = "#root.methodName", sync = true)
    public AjaxResult selectUserById(@PathVariable Long id) {
        String key = "selectUserById";
        RLock readLock = cacheService.getReadLock(key);
        readLock.lock(20, TimeUnit.SECONDS);
        SysUser user = new SysUser();
        try {
            logger.info("加锁成功，开始执行业务......");
            user = userService.getById(id);
            logger.info("从数据库中查询用户信息.....");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户数据异常", e.getMessage());
        } finally {
            logger.info("释放锁，业务执行结束......");
            readLock.unlock();
        }
        return AjaxResult.ok().data("user", user);
    }

    /**
     * 修改用户信息(失效模式)
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    @ApiOperation(value = "修改用户信息")
    @CacheEvict(value = "user", key = "'updateUser'")
    public AjaxResult updateUser(@RequestBody SysUser user) {
        boolean flag = userService.updateById(user);
        if (flag) {
            return AjaxResult.ok();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 修改用户数据(双写模式)
     * @param user
     * @return
     */
    @PutMapping("/updateUserInfo")
    @ApiOperation(value = "修改用户数据")
    @CachePut(value = "user", key = "#root.methodName")
    public AjaxResult updateUserInfo(@RequestBody SysUser user) {
        boolean flag = userService.updateById(user);
        if (flag) {
            return AjaxResult.ok();
        } else {
            return AjaxResult.error();
        }
    }

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
