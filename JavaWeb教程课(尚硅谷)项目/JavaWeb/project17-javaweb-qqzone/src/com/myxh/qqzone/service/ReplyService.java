package com.myxh.qqzone.service;

import com.myxh.qqzone.pojo.Reply;
import com.myxh.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public interface ReplyService
{
    /**
     * 根据 topic 的 id 获取关联的所有的回复
     */
    List<Reply> getReplyListByTopicId(Integer topicId);

    /**
     * 添加回复
     */
    void addReply(Reply reply);

    /**
     * 删除指定的回复
     */
    void deleteReply(Integer replyId);

    /**
     * 删除指定的日志关联的所有回复
     */
    void deleteReplyList(Topic topic);
}
