package com.myxh.qqzone.service;

import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public interface TopicService
{
    /**
     * 查询特定用户的日志列表
     */
    List<Topic> getTopicList(UserBasic userBasic);

    /**
     * 根 id 获取特定 topic
     */
    Topic getTopicById(Integer id);

    /**
     * 根据 id 获取指定的 topic 信息, 包含这个 topic 关联的作音信息
     */
    Topic getTopicAndAuthorById(Integer id);

    /**
     * 删除特定的 topic
     */
    void deleteTopic(Integer id);
}
