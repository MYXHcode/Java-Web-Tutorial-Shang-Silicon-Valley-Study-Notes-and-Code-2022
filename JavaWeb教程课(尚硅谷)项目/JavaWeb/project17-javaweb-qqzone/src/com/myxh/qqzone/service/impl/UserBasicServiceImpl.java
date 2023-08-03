package com.myxh.qqzone.service.impl;

import com.myxh.qqzone.dao.UserBasicDao;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public class UserBasicServiceImpl implements UserBasicService
{
    private final UserBasicDao userBasicDao = null;

    /**
     * 用户登录
     */
    @Override
    public UserBasic login(String loginId, String password)
    {
        UserBasic userBasic = userBasicDao.getUserBasic(loginId, password);

        return userBasic;
    }

    /**
     * 获取好友列表
     */
    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic)
    {
        List<UserBasic> userBasicList = userBasicDao.getUserBasicList(userBasic);
        List<UserBasic> friendList = new ArrayList<>(userBasicList.size());

        for (UserBasic friend : userBasicList)
        {
            friend = userBasicDao.getUserBasicById(friend.getId());
            friendList.add(friend);
        }

        return friendList;
    }

    /**
     * 根据 id 获取指定的用户信息
     */
    @Override
    public UserBasic getUserBasicById(Integer id)
    {
        UserBasic userBasicById = userBasicDao.getUserBasicById(id);

        return userBasicById;
    }
}
