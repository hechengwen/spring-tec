package hcw.tec.service.impl;

import hcw.tec.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/4/24 15:26
 * Description:
 * Others:
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public String getUser(String name) {
        logger.info("{},UserServiceImpl provider server start.....",name);
        return name + "调用 UserServiceImpl dubbo接口成功......";
    }
}
