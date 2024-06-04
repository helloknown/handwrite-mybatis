package com.gao.mybatis.test.dao;

public interface IUserDao {

    String queryUserInfoById(String number);

    Integer getUserAge(String uId);
}
