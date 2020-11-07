package com.xlhj.email.controller;

import com.xlhj.email.common.ResultData;
import com.xlhj.email.entity.Email;
import com.xlhj.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lcj
 * @Date: 2020/11/6 14:45
 * @Description: 测试邮件发送
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
        boolean flag = emailService.sendSimpleMail(email.getMail(), email.getSubject(), email.getText());
        if (flag) {
            return ResultData.ok().message("邮件发送成功!");
        } else {
            return ResultData.error().message("邮件发送失败!");
        }
    }

    /**
     * 使用JavaMailSender发送简单邮件
     * @param email
     */
    @PostMapping("/sendSimpleMailForJava")
    public ResultData sendSimpleMailForJava(@RequestBody Email email) {
        boolean flag = emailService.sendSimpleMailForJava(email.getMail(), email.getSubject(), email.getText());
        if (flag) {
            return ResultData.ok().message("邮件发送成功!");
        } else {
            return ResultData.error().message("邮件发送失败!");
        }
    }

    /**
     * 使用JavaMailSenderImpl发送邮件
     * @param email
     */
    @PostMapping("/sendSimpleMailForJavaAndHelper")
    public ResultData sendSimpleMailForJavaAndHelper(@RequestBody Email email) {
        boolean flag = emailService.sendSimpleMailForJavaAndHelper(email.getMail(), email.getSubject(), email.getText());
        if (flag) {
            return ResultData.ok().message("邮件发送成功!");
        } else {
            return ResultData.error().message("邮件发送失败!");
        }
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
            return ResultData.ok().message("发送带附件邮件成功!");
        } else {
            return ResultData.error().message("发送带附件邮件失败!");
        }
    }

    /**
     * 发送html图片邮件
     * @param email
     * @return
     */
    @PostMapping("/sendHtmlPictureMail")
    public ResultData sendHtmlPictureMail(@RequestBody Email email) {
        boolean flag = emailService.sendHtmlPictureMail(email.getMail(), email.getSubject(), email.getText(), email.getFilePath());
        if (flag) {
            return ResultData.ok().message("发送HTML图片邮件成功!");
        } else {
            return ResultData.error().message("发送HTML图片邮件失败!");
        }
    }

    /**
     * 发送html邮件
     * @param email
     * @return
     */
    @PostMapping("/sendHtmlMail")
    public ResultData sendHtmlMail(@RequestBody Email email) {
        boolean flag = emailService.sendHtmlMail(email.getMail(), email.getSubject(), email.getText());
        if (flag) {
            return ResultData.ok().message("发送HTML邮件成功!");
        } else {
            return ResultData.error().message("发送HTML邮件失败!");
        }
    }

    /**
     * 发送html模板邮件
     * @param email
     * @return
     */
    @PostMapping("/sendTemplateMail")
    public ResultData sendTemplateMail(@RequestBody Email email) {
        boolean flag = emailService.sendTemplateMail(email.getMail(), email.getSubject(), email.getTemplate());
        if (flag) {
            return ResultData.ok().message("发送模板邮件成功!");
        } else {
            return ResultData.error().message("发送模板邮件失败!");
        }
    }
}
