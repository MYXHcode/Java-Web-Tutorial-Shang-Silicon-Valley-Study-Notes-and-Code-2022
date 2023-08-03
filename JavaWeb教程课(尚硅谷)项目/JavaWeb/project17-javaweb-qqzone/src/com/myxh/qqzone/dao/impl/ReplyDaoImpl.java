package com.myxh.qqzone.dao.impl;

import com.myxh.qqzone.dao.ReplyDao;
import com.myxh.qqzone.pojo.Reply;
import com.myxh.qqzone.pojo.Topic;
import com.myxh.ssm.basedao.BaseDao;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public class ReplyDaoImpl extends BaseDao<Reply> implements ReplyDao
{
    /**
     * 获取指定日志的回复列表
     */
    @Override
    public List<Reply> getReplyList(Topic topic)
    {
        String sql = "select * from t_reply where topic = ?";

        return executeQuery(sql, topic.getId());
    }

    /**
     * 添加回复
     */
    @Override
    public void addReply(Reply reply)
    {
        String sql = "insert into t_reply values(0, ?, ?, ?, ?)";

        executeUpdate(sql, reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
    }

    /**
     * 删除回复
     */
    @Override
    public void deleteReply(Integer id)
    {
        String sql = "delete from t_reply where id = ?";

        executeUpdate(sql, id);
    }

    /**
     * 根据 id 获取指定的 Reply
     */
    @Override
    public Reply getreplyById(Integer id)
    {
        String sql = "select * from t_reply where id = ?";

        return load(sql, id);
    }
}
