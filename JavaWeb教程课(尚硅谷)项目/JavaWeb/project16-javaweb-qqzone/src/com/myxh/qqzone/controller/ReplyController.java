package com.myxh.qqzone.controller;

import com.myxh.qqzone.pojo.Reply;
import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public class ReplyController
{
    private final ReplyService replyService = null;

    public String addReply(String content, Integer topicId, HttpSession session)
    {
        UserBasic author = (UserBasic) session.getAttribute("userBasic");

        Reply reply = new Reply(content, LocalDateTime.now(), author, new Topic(topicId));

        replyService.addReply(reply);

        // 重定向到 detail.html 页面
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }

    public String deleteReply(Integer replyId, Integer topicId)
    {
        replyService.deleteReply(replyId);

        // 重定向到 detail.html 页面
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}
