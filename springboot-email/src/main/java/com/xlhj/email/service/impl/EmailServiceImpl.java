package com.xlhj.email.service.impl;

import com.xlhj.email.service.EmailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    /**
     * 使用MailSender发送简单邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    @Override
    public boolean sendSimpleMail(String mail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(mail);
        message.setSubject(subject);
        message.setText(text);
        try {
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 使用JavaMailSender发送简单邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    @Override
    public boolean sendSimpleMailForJava(String mail, String subject, String text) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
                mimeMessage.setFrom(new InternetAddress(username));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(text);
            }
        };
        try {
            javaMailSender.send(preparator);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 使用JavaMailSenderImpl发送邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    @Override
    public boolean sendSimpleMailForJavaAndHelper(String mail, String subject, String text) {
        javaMailSenderImpl.setHost(host);
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(username);
            helper.setTo(mail);
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSenderImpl.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送带附件邮件
     * @param mail 对方邮箱
     * @param subject 主题
     * @param text 内容
     * @param filePath 文件路径
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
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
            helper.addAttachment(fileName, resource);
            javaMailSenderImpl.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送html图片邮件
     * @param mail 对方邮箱
     * @param subject 主题
     * @param text 内容
     * @param filePath 文件路径
     * @return
     */
    @Override
    public boolean sendHtmlPictureMail(String mail, String subject, String text, String filePath) {
        javaMailSenderImpl.setHost(host);
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(mail);
            helper.setSubject(subject);
            FileSystemResource resource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
            helper.setText("<html><body><img src='cid:" + fileName + "'></body></html>", true);
            helper.addInline(fileName, resource);
            javaMailSenderImpl.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送html邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     * @return
     */
    @Override
    public boolean sendHtmlMail(String mail, String subject, String text) {
        javaMailSenderImpl.setHost(host);
        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(mail);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSenderImpl.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送模板邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param template 模板名称
     * @return
     */
    @Override
    public boolean sendTemplateMail(String mail, String subject, String template) {
        try {
            Template temp = freeMarkerConfig.getConfiguration().getTemplate(template);
            Map<String, Object> map = new HashMap<>();
            map.put("username", mail);
            String templateHtml = FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
            this.sendHtmlMail(mail, subject, templateHtml);
            return true;
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
