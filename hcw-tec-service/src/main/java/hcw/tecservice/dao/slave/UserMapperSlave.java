package hcw.tecservice.dao.slave;

import hcw.tec.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 16:28
 * Description:
 * Others:
 */
@Repository
public interface UserMapperSlave {
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
    User login(@Param("username") String username, @Param("password") String password);
}
