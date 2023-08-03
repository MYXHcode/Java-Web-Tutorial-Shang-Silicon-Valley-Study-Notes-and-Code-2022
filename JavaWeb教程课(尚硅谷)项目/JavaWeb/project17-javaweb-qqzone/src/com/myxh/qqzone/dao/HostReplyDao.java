package com.myxh.qqzone.dao;

import com.myxh.qqzone.pojo.HostReply;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public interface HostReplyDao
{
    /**
     * 根据 replyId 查询关联的 HostReply 实体
     */
    HostReply getHostReplyByReplyId(Integer replyId);

    /**
     * 删除特定的 HostReply
     */
    void deleteHostReplyByHostReplyId(Integer hostReplyId);
}
