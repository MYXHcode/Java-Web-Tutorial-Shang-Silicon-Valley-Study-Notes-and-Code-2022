package com.myxh.book.service;

import com.myxh.book.pojo.User;

/**
 * @author MYXH
 * @date 2023/7/23
 */
public interface UserService
{
    /**
     * 账号登录
     */
    User login(String name, String password);
}
