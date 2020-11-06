package com.xlhj.email.service;

/**
 * @Author: lcj
 * @Date: 2020/11/6 14:09
 * @Description: 发送邮件业务接口
 * @Version: 0.0.1
 */
public interface EmailService {

    /**
     * 使用MailSender发送简单邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    void sendSimpleMail(String mail, String subject, String text);

    /**
     * 使用JavaMailSender发送简单邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    void sendSimpleMailForJava(String mail, String subject, String text);

    /**
     * 使用JavaMailSenderImpl发送邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    void sendSimpleMailForJavaAndHelper(String mail, String subject, String text);

    /**
     * 发送带附件邮件
     * @param mail
     * @param subject
     * @param text
     * @param filePath
     */
    boolean sendAttachmentMail(String mail, String subject, String text, String filePath);

    void sendHtmlMail();
}
