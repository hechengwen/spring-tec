package hcw.tec.service.impl;

import hcw.tec.dao.UserMapper;
import hcw.tec.pojo.User;
import hcw.tec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 16:28
 * Description:
 * Others:
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }
}
