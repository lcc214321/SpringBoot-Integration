package com.xlhj.securityjwt.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lcj
 * @Date: 2020/10/30 15:14
 * @Description: 用户登录信息实体类
 * @Version: 0.0.1
 */
@Data
public class LoginUser implements Serializable {

    /**用户名*/
    private String username;
    /**密码*/
    private String password;
}
