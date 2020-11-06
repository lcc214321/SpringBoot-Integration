package com.xlhj.email.controller;

import com.xlhj.email.common.ResultData;
import com.xlhj.email.entity.Email;
import com.xlhj.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lcj
 * @Date: 2020/11/6 14:45
 * @Description: TODO
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 使用MailSender发送简单邮件
     * @param email
     */
    @PostMapping("/sendSimpleMail")
    public ResultData sendSimpleMail(@RequestBody Email email) {
        emailService.sendSimpleMail(email.getMail(), email.getSubject(), email.getText());
        return ResultData.ok().message("邮件发送成功!");
    }

    /**
     * 使用JavaMailSender发送简单邮件
     * @param email
     */
    @PostMapping("/sendSimpleMailForJava")
    public ResultData sendSimpleMailForJava(@RequestBody Email email) {
        emailService.sendSimpleMailForJava(email.getMail(), email.getSubject(), email.getText());
        return ResultData.ok().message("邮件发送成功!");
    }

    /**
     * 使用JavaMailSenderImpl发送邮件
     * @param email
     */
    @PostMapping("/sendSimpleMailForJavaAndHelper")
    public ResultData sendSimpleMailForJavaAndHelper(@RequestBody Email email) {
        emailService.sendSimpleMailForJavaAndHelper(email.getMail(), email.getSubject(), email.getText());
        return ResultData.ok().message("邮件发送成功!");
    }

    /**
     * 发送带附件邮件
     * @param email
     * @return
     */
    @PostMapping("/sendAttachmentMail")
    public ResultData sendAttachmentMail(@RequestBody Email email) {
        boolean flag = emailService.sendAttachmentMail(email.getMail(), email.getSubject(), email.getText(), email.getFilePath());
        if (flag) {
            return ResultData.ok().message("邮件发送成功!");
        } else {
            return ResultData.ok().message("邮件发送失败!");
        }
    }
}
