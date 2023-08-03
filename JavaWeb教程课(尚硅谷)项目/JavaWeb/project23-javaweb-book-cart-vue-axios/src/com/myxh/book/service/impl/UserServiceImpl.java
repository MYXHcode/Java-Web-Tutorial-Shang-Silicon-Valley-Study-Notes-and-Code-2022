package com.myxh.book.service.impl;

import com.myxh.book.dao.UserDao;
import com.myxh.book.pojo.User;
import com.myxh.book.service.UserService;

/**
 * @author MYXH
 * @date 2023/8/2
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

    /**
     * 账号注册
     */
    @Override
    public void regist(User user)
    {
        userDao.addUser(user);
    }

    /**
     * 根据账号获取特定用户信息
     */
    @Override
    public User getUser(String name)
    {
        return userDao.getUser(name);
    }
}
