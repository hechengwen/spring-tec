package hcw.tec.email.service.impl;

import hcw.tec.email.auth.MailAuthenticator;
import hcw.tec.email.config.EmailConfig;
import hcw.tec.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/25 14:10
 * Description:
 * Others:
 */
@Service
public class EmailServiceImpl implements EmailService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static String senderNick = "何承文";

    @Autowired
    private EmailConfig config;

    private final Properties properties = System.getProperties();

    private Session session; // 邮件会话对象

    private MimeMessage mimeMsg; // MIME邮件对象

    private Multipart mp;   // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

    @PostConstruct
    public void init() {
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", config.getSmtpHost());
        properties.put("mail.timeout", config.getTimeOut());
        properties.put("mail.smtp.port", config.getSmtpPort());
        properties.put("mail.transport.protocol", config.getTransportProtocol());

        // 创建session
        session = Session.getInstance(properties, new MailAuthenticator(config.getUsername(), config.getPassword()));
    }

    @Override
    public boolean sendEmail(String[] to, String[] copyto, String subject, String content, String[] fileList) {
        logger.info("to=[{}] copy=[{}] subject=[{}] content=[{}] fileList=[{}]", to, copyto, subject, content, fileList);

        try {
            mimeMsg = new MimeMessage(session);
            mp = new MimeMultipart();
            // 自定义发件人昵称
            String nick = "";
            try {
                nick = javax.mail.internet.MimeUtility.encodeText(senderNick);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 设置发信人
            mimeMsg.setFrom(new InternetAddress(config.getUsername(), nick));
            // 设置收件人
            if (to != null && to.length > 0) {
                String toListStr = getMailList(to);
                mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toListStr));
            }

            // 设置抄送人
            if (copyto != null && copyto.length > 0) {
                String ccListStr = getMailList(copyto);
                mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccListStr));
            }

            // 设置主题
            mimeMsg.setSubject(subject);

            // 设置正文
            BodyPart bp = new MimeBodyPart();
            bp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(bp);

            // 设置附件
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    bp = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(fileList[i]);
                    bp.setDataHandler(new DataHandler(fds));
                    bp.setFileName(MimeUtility.encodeText(fds.getName(), "UTF-8", "B"));
                    mp.addBodyPart(bp);
                }
            }
            mimeMsg.setContent(mp);
            Transport.send(mimeMsg);
            logger.info("邮件发送成功...");
            return true;
        } catch (AddressException e) {
            logger.error("邮件发送失败...");
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            logger.error("邮件发送失败...");
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            logger.error("邮件发送失败...");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendEmail(String to, String cc, String subject, String content, String[] fileList) {
        logger.info("to=[{}] cc=[{}] subject=[{}] content=[{}] fileList=[{}]", to, cc, subject, content, fileList);
        try {
            mimeMsg = new MimeMessage(session);
            mp = new MimeMultipart();

            // 自定义发件人昵称
            String nick = "";
            try {
                nick = javax.mail.internet.MimeUtility.encodeText(senderNick);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mimeMsg.setFrom(new InternetAddress(config.getUsername(), nick));
            // 设置收件人
            if (to != null && to.length() > 0) {
                mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            }
            // 设置抄送人
            if (cc != null && cc.length() > 0) {
                mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }

            // 设置主题
            mimeMsg.setSubject(subject);
            // 设置正文
            BodyPart bp = new MimeBodyPart();
            bp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(bp);
            // 设置附件
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    bp = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(fileList[i]);
                    bp.setFileName(MimeUtility.encodeText(fds.getName(), "UTF-8", "B"));
                    bp.setDataHandler(new DataHandler(fds));
                    mp.addBodyPart(bp);
                }
            }
            mimeMsg.setContent(mp);
            Transport.send(mimeMsg);
            logger.info("邮件发送成功...");
            return true;
        } catch (AddressException e) {
            logger.error("邮件发送失败...");
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            logger.error("邮件发送失败...");
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            logger.error("邮件发送失败...");
            e.printStackTrace();
            return false;
        }
    }

    // 将字符串数组转换成字符串，并且用","隔开
    public String getMailList(String[] mailArray) {
        StringBuffer toList = new StringBuffer();
        int length = mailArray.length;
        if (mailArray != null && length < 2) {
            toList.append(mailArray[0]);
        } else {
            for (int i = 0; i < length; i++) {
                toList.append(mailArray[i]);
                if (i != (length - 1)) {
                    toList.append(",");
                }

            }
        }
        return toList.toString();
    }


}
