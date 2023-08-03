package com.myxh.qqzone.dao;

import com.myxh.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/17
 */
public interface UserBasicDao
{
    /**
     * 根据账号和密码获取特定用户信息
     */
    UserBasic getUserBasic(String loginId, String password);

    /**
     * 获取指定用户的所有好友列表
     */
    List<UserBasic> getUserBasicList(UserBasic userBasic);

    /**
     * 根据 id 查询 userBasic 的信息
     */
    UserBasic getUserBasicById(Integer id);
}
