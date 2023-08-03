package com.myxh.qqzone.dao.impl;

import com.myxh.qqzone.dao.UserBasicDao;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.ssm.basedao.BaseDao;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public class UserBasicDaoImpl extends BaseDao<UserBasic> implements UserBasicDao
{
    /**
     * 根据账号和密码获取特定用户信息
     */
    @Override
    public UserBasic getUserBasic(String loginId, String password)
    {
        String sql = "select * from t_user_basic where loginId = ? and password = ?";

        return super.load(sql, loginId, password);
    }

    /**
     * 获取指定用户的所有好友列表
     */
    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic)
    {
        String sql = "select fid as id from t_friend where uid = ?";

        return super.executeQuery(sql, userBasic.getId());
    }

    /**
     * 根据 id 查询 userBasic 的信息
     */
    @Override
    public UserBasic getUserBasicById(Integer id)
    {
        String sql = "select * from t_user_basic where id = ?";

        return super.load(sql, id);
    }
}
