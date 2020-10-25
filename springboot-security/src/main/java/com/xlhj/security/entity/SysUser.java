package com.xlhj.security.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName SysUser
 * @Description 用户表
 * @Author liucaijing
 * @Date 2020/10/25 19:06
 * @Version 1.0
 */
@Data
public class SysUser implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userName;

    private String realName;

    private String password;

    private Integer sex;

    private String avatar;

    private Integer status;

    @TableLogic(value = "0", delval = "1")
    private Boolean delFlag;

    private String createBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String updateBy;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String remark;

}
