package com.xlhj.security.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: lcj
 * @Date: 2020/10/27 9:17
 * @Description: 菜单管理实体类
 * @Version: 0.0.1
 */
@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 3418187222507724958L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String url;

    private Integer menuType;

    private Integer visible;

    private String perms;

    private String icon;

    private String createBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String updateBy;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String remark;
}
