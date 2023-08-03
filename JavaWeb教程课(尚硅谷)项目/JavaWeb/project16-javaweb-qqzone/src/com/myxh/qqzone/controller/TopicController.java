package com.myxh.qqzone.controller;

import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public class TopicController
{
    private final TopicService topicService = null;

    public String topicDetail(Integer id, HttpSession session)
    {
        Topic topicById = topicService.getTopicById(id);

        session.setAttribute("topic", topicById);

        return "frames/detail";
    }

     public String deleteTopic(Integer topicId)
    {
        topicService.deleteTopic(topicId);

        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session)
    {
        // 从 session 中获取当前用户信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");

        // 再次查询当前用户关联的所有的日志
        List<Topic> topicList = topicService.getTopicList(userBasic);

        // 设置关联的日志列表, 因为之前 session 中关联的 friend 的 topicList 和此刻数据库中的数据不一致
        userBasic.setTopicList(topicList);

        // 重新覆盖 friend 中的信息, 而不覆盖 userBasic, 因为 main.html 页面迭代的是 friend 这个 key 中的数据
        session.setAttribute("friend", userBasic);

        return "frames/main";
    }
}
