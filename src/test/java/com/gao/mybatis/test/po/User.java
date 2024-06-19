package com.gao.mybatis.test.po;

import java.util.Date;

public class User {

    private Long id;
    public String userId;          // 用户ID
    private String userHead;        // 头像
    private String name;
    private String address;         // 地址
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public User(Long id, String userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
