package com.myxh.qqzone.service.impl;

import com.myxh.qqzone.dao.ReplyDao;
import com.myxh.qqzone.pojo.HostReply;
import com.myxh.qqzone.pojo.Reply;
import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.qqzone.service.HostReplyService;
import com.myxh.qqzone.service.ReplyService;
import com.myxh.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public class ReplyServiceImpl implements ReplyService
{
    private final ReplyDao replyDao = null;

    /*
    此处引入的是其他 POJO 对应的 Service 接口, 而不是 DAO 接口,
    其他 POJO 对应的业务逻辑是封装在 Service 层的, 需要调用业务逻辑方法, 而不要去深入考虑内部的细节
     */
    private final HostReplyService hostReplyService = null;

    private final UserBasicService userBasicService = null;

    /**
     * 根据 topic 的 id 获取关联的所有的回复
     */
    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId)
    {
        List<Reply> replyList = replyDao.getReplyList(new Topic(topicId));

        for (Reply reply : replyList)
        {
            // 1. 将关联的作者设置进去
            Integer id = reply.getAuthor().getId();

            UserBasic author = userBasicService.getUserBasicById(id);

            reply.setAuthor(author);

            // 2. 将关联的 HostReply 设置进去
            HostReply hostReplyByReplyId = hostReplyService.getHostReplyByReplyId(reply.getId());

            reply.setHostReply(hostReplyByReplyId);
        }

        return replyList;
    }

    /**
     * 添加回复
     */
    @Override
    public void addReply(Reply reply)
    {
        replyDao.addReply(reply);
    }

    /**
     * 删除指定的回复
     */
    @Override
    public void deleteReply(Integer replyId)
    {
        // 1. 根据 id 获取到 reply
        Reply reply = replyDao.getreplyById(replyId);

        if (reply != null)
        {
            // 2. 加果 reply 有关联的 hostReply, 则先删除 hostReply
            HostReply hostReplyByReplyId = hostReplyService.getHostReplyByReplyId(reply.getId());

            if (hostReplyByReplyId != null)
            {
                hostReplyService.deleteHostReplyByHostReplyId(hostReplyByReplyId.getId());
            }

            // 3. 册除 reply
            replyDao.deleteReply(replyId);
        }
    }

    /**
     * 删除指定的日志关联的所有回复
     */
    @Override
    public void deleteReplyList(Topic topic)
    {
        List<Reply> replyList = replyDao.getReplyList(topic);

        if (replyList != null)
        {
            for (Reply reply : replyList)
            {
                deleteReply(reply.getId());
            }
        }
    }
}
