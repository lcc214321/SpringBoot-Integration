package com.xlhj.redisson.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.xlhj.redisson.common.AjaxResult;
import com.xlhj.redisson.entity.SysUser;
import com.xlhj.redisson.service.RedissonService;
import com.xlhj.redisson.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SysUserController
 * @Description 用户管理Controller--测试redis
 * @Author liucaijing
 * @Date 2020/10/9 23:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/redis/user")
public class SysUserController {

    private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private RedissonService redissonService;
    @Autowired
    private SysUserService userService;

    /**
     * 根据用户ID查询用户信息并实现分布式锁
     * @param id
     * @return
     */
    @GetMapping("/selectUserForLockById/{id}")
    @ApiOperation(value = "根据用户ID查询用户信息并加锁")
    public AjaxResult selectUserForLockById(@PathVariable Long id) {
        String key = "user_" + id;
        RLock readLock = redissonService.getReadLock(key);
        readLock.lock();//加锁
        //readLock.lock(10, TimeUnit.SECONDS);可以设置锁的过期时间，如果不设置redisson默认30秒
        SysUser user = new SysUser();
        try {
            logger.info("加锁成功，开始执行查询业务...");
            user = userService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("释放锁....");
            readLock.unlock();
        }
        return AjaxResult.ok().data("user", user);
    }

    /**
     * 修改用户信息并加锁
     * @param user
     * @return
     */
    @PutMapping("/updateUserForLock")
    @ApiOperation(value = "修改用户信息并加锁")
    public AjaxResult updateUserForLock(@RequestBody SysUser user) {
        String key = "user_" + user.getId();
        RLock writeLock = redissonService.getWriteLock(key);
        writeLock.lock();
        boolean flag = false;
        try {
            logger.info("获取写锁成功，开始执行业务......");
            flag = userService.updateById(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("业务执行完成，释放写锁....");
            writeLock.unlock();
        }
        return AjaxResult.ok().data("flag", flag);
    }

    /**
     * 查询用户信息(信号量测试)
     * @param id
     * @return
     */
    @GetMapping("/selectUserForSemaphoreById/{id}")
    @ApiOperation(value = "查询用户信息测试信号量")
    public AjaxResult selectUserForSemaphoreById(@PathVariable Long id) {
        String key = "user_semaphore_" + id;
        RSemaphore semaphore = redissonService.getSemaphore(key);
        //设置初始信号量数量
        semaphore.trySetPermits(5);
        SysUser user = new SysUser();
        try {
            boolean flag = semaphore.tryAcquire();
            if (flag) {
                logger.info("获取信号量成功，开始执行任务......");
                user = userService.getById(id);
                logger.info("业务执行完成，释放信号量......");
                semaphore.release();
            } else {
                logger.info("获取信号量失败，直接返回消息......");
                user = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.ok().data("user", user);
    }
}
