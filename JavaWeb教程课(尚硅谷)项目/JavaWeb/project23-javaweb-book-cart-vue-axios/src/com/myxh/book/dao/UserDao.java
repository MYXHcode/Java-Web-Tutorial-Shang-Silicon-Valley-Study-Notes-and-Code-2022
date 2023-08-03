package com.myxh.book.dao;

import com.myxh.book.pojo.User;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public interface UserDao
{
    /**
     * 根据账号获取特定用户信息
     */
    User getUser(String name);

    /**
     * 根据账号和密码获取特定用户信息
     */
    User getUser(String name, String password);

    /**
     * 添加用户
     */
    void addUser(User user);
}
