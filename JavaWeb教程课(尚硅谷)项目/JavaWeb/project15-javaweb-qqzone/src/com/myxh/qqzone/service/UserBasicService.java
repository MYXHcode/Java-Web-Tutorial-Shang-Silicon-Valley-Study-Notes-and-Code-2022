package com.myxh.qqzone.service;

import com.myxh.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/17
 */
public interface UserBasicService
{
    /**
     * 用户登录
     */
    UserBasic login(String loginId, String password);

    /**
     * 获取好友列表
     */
    List<UserBasic> getFriendList(UserBasic userBasic);
}
