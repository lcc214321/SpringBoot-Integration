package com.xlhj.email.service.impl;

import com.xlhj.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: lcj
 * @Date: 2020/11/6 14:10
 * @Description: 发送邮件业务接口实现类
 * @Version: 0.0.1
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.host}")
    private String host;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;
    @Resource
    private JavaMailSenderImpl javaMailSenderImpl;

    /**
     * 使用MailSender发送简单邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    @Override
    public void sendSimpleMail(String mail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(mail);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    /**
     * 使用JavaMailSender发送简单邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    @Override
    public void sendSimpleMailForJava(String mail, String subject, String text) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
                mimeMessage.setFrom(new InternetAddress(username));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(text);
            }
        };
        javaMailSender.send(preparator);
    }

    /**
     * 使用JavaMailSenderImpl发送邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    @Override
    public void sendSimpleMailForJavaAndHelper(String mail, String subject, String text) {
        javaMailSenderImpl.setHost(host);
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(username);
            helper.setTo(mail);
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSenderImpl.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送带附件邮件
     * @param mail
     * @param subject
     * @param text
     * @param filePath
     */
    @Override
    public boolean sendAttachmentMail(String mail, String subject, String text, String filePath) {
        javaMailSenderImpl.setHost(host);
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(mail);
            helper.setSubject(subject);
            helper.setText(text, true);
            FileSystemResource resource = new FileSystemResource(new File(filePath));
            //String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment("test.txt", resource);
            javaMailSenderImpl.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void sendHtmlMail() {

    }
}
