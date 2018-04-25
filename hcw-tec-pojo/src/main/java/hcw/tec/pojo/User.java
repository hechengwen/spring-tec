package hcw.tec.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2017/11/29 13:34
 * Description:
 * Others:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    private static final long serialVersionUID = 2504467948968634865L;
    private String userId;
    private String userName;
    private String password;
    private String mobile;
    private String realName;
    private String email;
    private String company;
    private String address;
    private String idCard;
    private String sex;
    private Date createTime;

    public User(String userName,String mobile,String email){
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
    }
}
