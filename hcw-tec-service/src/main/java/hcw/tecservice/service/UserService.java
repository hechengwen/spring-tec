package hcw.tecservice.service;

import hcw.tec.pojo.User;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 16:27
 * Description:
 * Others:
 */
public interface UserService {
    /**
     * 插入数据
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}
