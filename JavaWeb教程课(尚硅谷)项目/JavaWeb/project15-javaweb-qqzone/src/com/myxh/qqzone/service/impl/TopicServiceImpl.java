package com.myxh.qqzone.service.impl;

import com.myxh.qqzone.dao.TopicDao;
import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.qqzone.service.TopicService;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/17
 */
public class TopicServiceImpl implements TopicService
{
    private final TopicDao topicDao = null;

    /**
     * 查询特定用户的日志列表
     */
    @Override
    public List<Topic> getTopicList(UserBasic userBasic)
    {
        List<Topic> topicList = topicDao.getTopicList(userBasic);

        return topicList;
    }
}
