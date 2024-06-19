package com.gao.mybatis.test.dao;

import com.gao.mybatis.test.po.User;

import java.util.List;

public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfo(User req);

    List<User> queryUserInfoList();

    int updateUserInfo(User user);

    void insertUserInfo(User user);

    int deleteUserInfoByUserId(String userId);
}
