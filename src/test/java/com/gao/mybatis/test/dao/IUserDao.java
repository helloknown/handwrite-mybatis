package com.gao.mybatis.test.dao;

import com.gao.mybatis.test.po.User;

public interface IUserDao {

    User queryUserInfoById(String id);

    Integer getUserAge(String uId);
}
