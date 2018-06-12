package hcw.tecservice.service;

import hcw.tec.pojo.User;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/18 13:45
 * Description:
 * Others:
 */
public interface SlaveUserService extends SlaveDataSourceService {
    /**
     * 插入数据
     *
     * @param user
     * @return
     */
    int insert(User user);
}
