package hcw.tecservice.service.impl;

import hcw.tec.pojo.User;
import hcw.tecservice.dao.slave.UserMapperSlave;
import hcw.tecservice.service.SlaveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/18 13:45
 * Description:
 * Others:
 */
@Service
public class SlaveUserServiceImpl extends BaseService implements SlaveUserService {

    @Autowired
    UserMapperSlave userMapperSlave;

    @Override
    public int insert(User user) {
        return userMapperSlave.insert(user);
    }

}
