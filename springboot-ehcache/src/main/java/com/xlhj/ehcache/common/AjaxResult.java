package com.xlhj.ehcache.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AjaxResult
 * @Description 统一返回结果类
 * @Author liucaijing
 * @Date 2020/9/3023:57
 * @Version 1.0
 */
@Data
public class AjaxResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private AjaxResult() {
    }

    /**
     * 成功静态方法
     * @return
     */
    public static AjaxResult ok() {
        AjaxResult result = new AjaxResult();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功!");
        return result;
    }

    /**
     * 失败静态方法
     * @return
     */
    public static AjaxResult error() {
        AjaxResult result = new AjaxResult();
        result.setSuccess(true);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败!");
        return result;
    }

    public AjaxResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public AjaxResult message(String message){
        this.setMessage(message);
        return this;
    }

    public AjaxResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public AjaxResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public AjaxResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
