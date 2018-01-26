package hcw.tec.email.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/25 14:09
 * Description:
 * Others:
 */
@Data
@Component
public class EmailConfig {

    // 用户名（登录邮箱）
    @Value("${mail.username}")
    private String username;

    // 邮箱授权码
    @Value("${mail.password}")
    private String password;

    // 服务器
    @Value("${mail.smtp.host}")
    private String smtpHost;

    // 超时时间
    @Value("${mail.timeout}")
    private String timeOut;

    // 端口号
    @Value("${mail.smtp.port}")
    private String smtpPort;

    // 传输协议
    @Value("${mail.transport.protocol}")
    private String transportProtocol;

}
