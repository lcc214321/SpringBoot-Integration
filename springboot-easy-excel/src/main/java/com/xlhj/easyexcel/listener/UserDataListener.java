package com.xlhj.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.xlhj.easyexcel.entity.SysUser;
import com.xlhj.easyexcel.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDataListener
 * @Description 用户模板读取类
 * @Author liucaijing
 * @Date 2020/11/8 14:13
 * @Version 1.0
 */
public class UserDataListener extends AnalysisEventListener<SysUser> {

    private static final Logger logger = LoggerFactory.getLogger(UserDataListener.class);
    private static final int BATCH_COUNT = 5;
    List<SysUser> userList = new ArrayList<SysUser>();

    @Autowired
    private SysUserService userService;

    /**
     * 数据解析
     * @param sysUser
     * @param context
     */
    @Override
    public void invoke(SysUser sysUser, AnalysisContext context) {
        logger.info("解析数据为:{}", JSON.toJSONString(sysUser));
        userList.add(sysUser);
        if (userList.size() >= BATCH_COUNT) {
            userService.saveBatch(userList);
            userList.clear();
        }
    }

    /**
     * 数据解析后处理(确保遗留数据也保存到数据库)
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        userService.saveBatch(userList);
        logger.info("所有数据解析完成!");
    }
}
