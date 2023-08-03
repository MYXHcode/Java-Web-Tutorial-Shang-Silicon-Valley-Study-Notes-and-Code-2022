package com.myxh.qqzone.service;

import com.myxh.qqzone.pojo.HostReply;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public interface HostReplyService
{
    /**
     * 根据 reply 的 id 获取关联的所有的主人回复
     */
    HostReply getHostReplyByReplyId(Integer replyId);


    /**
     * 根据 hostReplyId 删除指定的主人回复
     */
    void deleteHostReplyByHostReplyId(Integer hostReplyId);
}
