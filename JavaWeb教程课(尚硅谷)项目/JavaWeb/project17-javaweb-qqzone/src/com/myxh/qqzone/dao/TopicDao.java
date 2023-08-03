package com.myxh.qqzone.dao;

import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public interface TopicDao
{
    /**
     * 获取指定用户的日志列表
     */
    List<Topic> getTopicList(UserBasic userBasic);

    /**
     * 添加加日志
     */
    void addTopic(Topic topic);

    /**
     * 删除日志
     */
    void deleteTopic (Topic topic);

    /**
     * 获取特定日志信息
     */
    Topic getTopic(Integer id);
}
