package com.myxh.qqzone.controller;

import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.qqzone.service.TopicService;
import com.myxh.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/17
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

            session.setAttribute("userBasic", userBasic);

            return "index";
        }
        else
        {
            return "login";
        }
    }
}
