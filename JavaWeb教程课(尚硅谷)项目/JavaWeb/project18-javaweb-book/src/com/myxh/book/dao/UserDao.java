package com.myxh.book.dao;

import com.myxh.book.pojo.User;

/**
 * @author MYXH
 * @date 2023/7/23
 */
public interface UserDao
{
    /**
     * 根据账号和密码获取特定用户信息
     */
    User getUser(String name, String password);
}
