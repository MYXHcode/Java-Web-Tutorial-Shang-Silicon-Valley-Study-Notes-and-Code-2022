package com.myxh.book.dao.impl;

import com.myxh.book.dao.UserDao;
import com.myxh.book.pojo.User;
import com.myxh.ssm.basedao.BaseDao;

/**
 * @author MYXH
 * @date 2023/7/28
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao
{
    /**
     * 根据账号和密码获取特定用户信息
     */
    @Override
    public User getUser(String name, String password)
    {
        String sql = "select * from t_user where name = ? and password = ?";

        return load(sql, name, password);
    }
}
