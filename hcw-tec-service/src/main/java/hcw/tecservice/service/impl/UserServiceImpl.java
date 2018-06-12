package hcw.tecservice.service.impl;

import hcw.tec.pojo.User;
import hcw.tecservice.dao.master.UserMapper;
import hcw.tecservice.dao.slave.UserMapperSlave;
import hcw.tecservice.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 16:28
 * Description:
 * Others:
 */
@Service
public class UserServiceImpl extends BaseService implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int batchInsert(List<User> list) {
        return userMapper.batchInsert(list);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }

}
