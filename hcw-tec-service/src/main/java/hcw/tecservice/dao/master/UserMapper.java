package hcw.tecservice.dao.master;

import hcw.tec.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 16:28
 * Description:
 * Others:
 */
@Repository
public interface UserMapper {
    /**
     * 插入数据
     * @param user
     * @return
     */
    int insert(User user);

    int batchInsert(@Param("list") List<User> list);

    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    User login(@Param("username") String username, @Param("password") String password);
}
