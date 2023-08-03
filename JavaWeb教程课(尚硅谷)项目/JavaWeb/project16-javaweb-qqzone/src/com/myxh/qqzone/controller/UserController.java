package com.myxh.qqzone.controller;

import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.qqzone.service.TopicService;
import com.myxh.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public class UserController
{
    private final UserBasicService userBasicService = null;
    private final TopicService topicService = null;
    public String login(String loginId, String password, HttpSession session)
    {
        // 1. 登录验证
        UserBasic userBasic = userBasicService.login(loginId, password);

        if (userBasic != null)
        {
            // 1.1 获取相关的好友信息
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);

            // 1.2 获取相关的日志列表信息
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            // userBasic 这个 key 保存的是登录者的信息
            session.setAttribute("userBasic", userBasic);

            // friend 这个 key 保存的是当前进入的空间的主人信息
            session.setAttribute("friend", userBasic);

            return "index";
        }
        else
        {
            return "login";
        }
    }

    public String friend(Integer id, HttpSession session)
    {
        // 1. 根据 id 获取指定的用户信息
        UserBasic currentFriend = userBasicService.getUserBasicById(id);

        List<Topic> topicList = topicService.getTopicList(currentFriend);

        currentFriend.setTopicList(topicList);

        session.setAttribute("friend", currentFriend);

        return "index";
    }
}
