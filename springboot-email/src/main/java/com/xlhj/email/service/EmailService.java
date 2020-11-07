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
    boolean sendSimpleMail(String mail, String subject, String text);

    /**
     * 使用JavaMailSender发送简单邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    boolean sendSimpleMailForJava(String mail, String subject, String text);

    /**
     * 使用JavaMailSenderImpl发送邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     */
    boolean sendSimpleMailForJavaAndHelper(String mail, String subject, String text);

    /**
     * 发送带附件邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     * @param filePath 文件路径
     */
    boolean sendAttachmentMail(String mail, String subject, String text, String filePath);

    /**
     * 发送html图片邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     * @param filePath 文件路径
     */
    boolean sendHtmlPictureMail(String mail, String subject, String text, String filePath);

    /**
     * 发送html邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param text 内容
     * @return
     */
    boolean sendHtmlMail(String mail, String subject, String text);

    /**
     * 发送模板邮件
     * @param mail 对方邮件地址
     * @param subject 主题
     * @param template 模板名称
     * @return
     */
    boolean sendTemplateMail(String mail, String subject, String template);
}
