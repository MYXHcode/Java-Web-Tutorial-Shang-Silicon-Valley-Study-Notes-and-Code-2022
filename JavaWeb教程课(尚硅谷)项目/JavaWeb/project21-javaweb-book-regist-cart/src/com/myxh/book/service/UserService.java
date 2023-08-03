package com.myxh.book.service;

import com.myxh.book.pojo.User;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public interface UserService
{
    /**
     * 账号登录
     */
    User login(String name, String password);

    /**
     * 账号注册
     */
    void regist(User user);

    /**
     * 根据账号获取特定用户信息
     */
    User getUser(String name);
}
