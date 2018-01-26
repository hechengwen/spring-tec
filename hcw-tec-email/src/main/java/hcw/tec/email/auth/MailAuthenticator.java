package hcw.tec.email.auth;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/25 14:11
 * Description:服务器邮箱登录验证
 * Others:
 */
public class MailAuthenticator extends Authenticator {

    private String username;

    private String password;

    public MailAuthenticator(String username,String password){
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

}
