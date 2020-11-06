package com.xlhj.email.entity;

import lombok.Data;

/**
 * @Author: lcj
 * @Date: 2020/11/6 15:12
 * @Description: 邮件发送实体类
 * @Version: 0.0.1
 */
@Data
public class Email {

    /**对方邮件地址*/
    private String mail;

    /**主题*/
    private String subject;

    /**内容*/
    private String text;

    /**附件路径*/
    private String filePath;
}
