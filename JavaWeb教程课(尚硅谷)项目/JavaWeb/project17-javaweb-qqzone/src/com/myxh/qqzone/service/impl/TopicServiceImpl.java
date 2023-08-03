package com.myxh.qqzone.service.impl;

import com.myxh.qqzone.dao.TopicDao;
import com.myxh.qqzone.pojo.Reply;
import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.qqzone.service.ReplyService;
import com.myxh.qqzone.service.TopicService;
import com.myxh.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public class TopicServiceImpl implements TopicService
{
    private final TopicDao topicDao = null;

    // 此处引用的是 replyService, 而不是 replyDao
    private final ReplyService replyService = null;

    private final UserBasicService userBasicService = null;

    /**
     * 查询特定用户的日志列表
     */
    @Override
    public List<Topic> getTopicList(UserBasic userBasic)
    {
        List<Topic> topicList = topicDao.getTopicList(userBasic);

        return topicList;
    }

    /**
     * 根 id 获取特定 topic
     */
    @Override
    public Topic getTopicById(Integer id)
    {
        Topic topic = getTopicAndAuthorById(id);

        List<Reply> replyListByTopicId = replyService.getReplyListByTopicId(topic.getId());

        topic.setReplyList(replyListByTopicId);

        return topic;
    }

    /**
     * 根据 id 获取指定的 topic 信息, 包含这个 topic 关联的作音信息
     */
    @Override
    public Topic getTopicAndAuthorById(Integer id)
    {
        Topic topic = topicDao.getTopic(id);

        UserBasic author = topic.getAuthor();

        author = userBasicService.getUserBasicById(author.getId());

        topic.setAuthor(author);

        return topic;
    }

    /**
     * 删除特定的 topic
     */
    @Override
    public void deleteTopic(Integer id)
    {
        Topic topic = topicDao.getTopic(id);

        if (topic != null)
        {
            replyService.deleteReplyList(topic);
            topicDao.deleteTopic(topic);
        }
    }
}
