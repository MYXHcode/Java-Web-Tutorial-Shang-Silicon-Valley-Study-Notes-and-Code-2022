package com.myxh.qqzone.service;

import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/17
 */
public interface TopicService
{
    /**
     * 查询特定用户的日志列表
     */
    List<Topic> getTopicList(UserBasic userBasic);
}
