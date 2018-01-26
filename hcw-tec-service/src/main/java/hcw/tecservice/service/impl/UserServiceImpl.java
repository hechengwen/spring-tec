package hcw.tecservice.service.impl;

import hcw.tecservice.dao.master.UserMapper;
import hcw.tec.pojo.User;
import hcw.tecservice.dao.slave.UserMapperSlave;
import hcw.tecservice.datasource.DatabaseContextHolder;
import hcw.tecservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Autowired
    UserMapperSlave userMapperSlave;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int insertSlave(User user) {
        return userMapperSlave.insert(user);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }

}
