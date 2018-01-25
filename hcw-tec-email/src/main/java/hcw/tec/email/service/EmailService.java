package hcw.tec.email.service;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/25 14:10
 * Description:
 * Others:
 */
public interface EmailService {

    /**
     * 发送邮件
     *
     * @param to       收件人
     * @param copyto   抄送
     * @param subject  主题
     * @param content  内容
     * @param fileList 附件列表
     * @return
     */
    boolean sendEmail(String[] to, String[] copyto, String subject, String content, String[] fileList);

    /**
     * 发送邮件
     *
     * @param from     发件人
     * @param to       收件人, 多个Email以英文逗号分隔
     * @param cc       抄送, 多个Email以英文逗号分隔
     * @param subject  主题
     * @param content  内容
     * @param fileList 附件列表
     * @return
     */
    boolean sendEmail(String to, String cc, String subject, String content, String[] fileList);

}
