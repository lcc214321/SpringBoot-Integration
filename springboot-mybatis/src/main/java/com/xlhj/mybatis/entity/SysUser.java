package com.xlhj.mybatis.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName SysUser
 * @Description 用户表
 * @Author liucaijing
 * @Date 2020/10/10 22:56
 * @Version 1.0
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 8018438623591477156L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别10:男;11:女;12:其他")
    private Integer sex;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "账号状态10:正常;20:锁定;30:注销")
    private Integer status;

    @ApiModelProperty(value = "删除标识0(true):未删除;1(false):已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;
}
