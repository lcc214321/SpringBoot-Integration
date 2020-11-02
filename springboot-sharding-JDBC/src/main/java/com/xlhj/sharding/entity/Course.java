package com.xlhj.sharding.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: lcj
 * @Date: 2020/11/2 10:28
 * @Description: 课程表
 * @Version: 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Course对象", description="课程表")
public class Course implements Serializable {

    private static final long serialVersionUID = -8529153037810492112L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "课程状态10:正常;20:注销")
    private Integer status;

}
