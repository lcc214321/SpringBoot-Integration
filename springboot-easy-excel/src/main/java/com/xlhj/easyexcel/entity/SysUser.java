package com.xlhj.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName SysUser
 * @Description 用户信息实体类
 * @Author liucaijing
 * @Date 2020/11/7 21:48
 * @Version 1.0
 */
@Data
public class SysUser {

    @ExcelProperty(value = "主键ID")
    private Long id;

    @ExcelProperty(value = "登录名")
    private String loginName;

    @ExcelProperty(value = "用户名")
    private String userName;

    @ExcelProperty(value = "密码")
    private String password;

    @ExcelProperty(value = "性别10:男;11:女;12:其他")
    private Integer sex;

    @ExcelProperty(value = "头像地址")
    private String avatar;

    @ExcelProperty(value = "账号状态10:正常;20:锁定;30:注销")
    private Integer status;

    @ExcelProperty(value = "删除标识0(true):未删除;1(false):已删除")
    private Integer delFlag;

    @ExcelProperty(value = "创建者")
    private String createBy;

    @ExcelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ExcelProperty(value = "更新者")
    private String updateBy;

    @ExcelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ExcelProperty(value = "备注")
    private String remark;
}
