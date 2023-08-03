package com.myxh.qqzone.dao;

import com.myxh.qqzone.pojo.Reply;
import com.myxh.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/17
 */
public interface ReplyDao
{
    /**
     * 获取指定日志的回复列表
     */
    List<Reply> getReplyList(Topic topic);

    /**
     * 添加回复
     */
    void addReply(Reply reply);

    /**
     * 删除回复
     */
    void deleteReply(Integer id);
}
