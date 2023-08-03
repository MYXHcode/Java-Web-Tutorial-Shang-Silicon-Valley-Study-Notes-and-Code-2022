package com.myxh.book.service.impl;

import com.myxh.book.dao.UserDao;
import com.myxh.book.pojo.User;
import com.myxh.book.service.UserService;

/**
 * @author MYXH
 * @date 2023/7/28
 */
public class UserServiceImpl implements UserService
{
    private UserDao userDao;

    /**
     * 账号登录
     */
    @Override
    public User login(String name, String password)
    {
        return userDao.getUser(name, password);
    }
}
