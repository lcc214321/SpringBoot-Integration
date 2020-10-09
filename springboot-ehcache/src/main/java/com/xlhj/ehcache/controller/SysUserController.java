package com.xlhj.ehcache.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlhj.ehcache.common.AjaxResult;
import com.xlhj.ehcache.entity.SysUser;
import com.xlhj.ehcache.service.EhcacheService;
import com.xlhj.ehcache.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lcj
 * @since 2020-10-01
 */
@RestController
@RequestMapping("/plus/user")
public class SysUserController {

    private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService userService;
    @Autowired
    private EhcacheService cacheService;

    /**
     * 根据登录名查询用户信息
     * @param loginName
     * @return
     */
    @GetMapping("getUserByLoginName/{loginName}")
    @ApiOperation(value = "根据登录名查询用户信息")
    public AjaxResult getUserByLoginName(@PathVariable String loginName) {
        String cacheValue = cacheService.getCache("userCache", loginName);
        if (null != cacheValue && !"".equals(cacheValue)) {
            logger.info("缓存中有查询的数据", cacheValue);
            return AjaxResult.ok().data("loginName", cacheValue);
        } else {
            QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
            wrapper.eq("login_name", loginName);
            SysUser user = userService.getOne(wrapper);
            logger.info("缓存中没有数据，从数据库查询", user.toString());
            cacheService.putCache("userCache", loginName, user.getLoginName());
            return AjaxResult.ok().data("loginName", user.getLoginName());
        }
    }
}

