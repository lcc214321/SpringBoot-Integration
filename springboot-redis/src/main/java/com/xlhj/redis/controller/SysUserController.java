package com.xlhj.redis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlhj.redis.common.AjaxResult;
import com.xlhj.redis.entity.SysUser;
import com.xlhj.redis.service.RedisService;
import com.xlhj.redis.service.SysUserService;
import io.swagger.annotations.ApiOperation;
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
    private RedisService redisService;
    @Autowired
    private SysUserService userService;

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping("/selectAllUserList")
    @ApiOperation(value = "查询用户列表")
    public AjaxResult selectAllUserList() {
        List<SysUser> userList = redisService.leftPop("userList");
        if (null == userList) {
            logger.info("缓存中没有数据，从数据库中获取", userList);
            userList = userService.list(null);
            Long num = redisService.rightPush("userList", userList, 300);
            return AjaxResult.ok().data("userList", userList).data("num", num);
        } else {
            logger.info("缓存中有数据，可以直接获取", userList);
            return AjaxResult.ok().data("userList", userList);
        }
    }

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/selectUserById/{id}")
    @ApiOperation(value = "根据ID查询用户信息")
    public AjaxResult selectUserById(@PathVariable Long id) {
        String key = "user_" + id;
        boolean exists = redisService.existsKey(key);
        SysUser user;
        if (exists) {
            user = redisService.get(key);
            logger.info("缓存中获取用户信息", user);
        } else {
            user = userService.getById(id);
            redisService.set(key, user, 300);
            logger.info("数据库中获取用户信息", user);
        }
        return AjaxResult.ok().data("user", user);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    @ApiOperation(value = "更新用户信息")
    public AjaxResult updateUser(@RequestBody SysUser user) {
        boolean flag = userService.updateById(user);
        if (flag) {
            String key = "user_" + user.getId();
            boolean exists = redisService.existsKey(key);
            if (exists) {
                redisService.delete(key);
                redisService.set(key, user, 300);
            }
        }
        return AjaxResult.ok();
    }

    /**
     * 根据ID删除用户信息
     * @param id
     * @return
     */
    @GetMapping("/deleteUserById")
    @ApiOperation(value = "根据ID删除用户信息")
    public AjaxResult deleteUserById(@PathVariable Long id) {
        boolean flag = userService.removeById(id);
        String key = "user_" + id;
        if (flag) {
            boolean exists = redisService.existsKey(key);
            if (exists) {
                redisService.delete(key);
                logger.info("删除缓存中用户信息");
            }
        }
        return AjaxResult.ok();
    }
}
