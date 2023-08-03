package com.myxh.qqzone.service.impl;

import com.myxh.qqzone.dao.HostReplyDao;
import com.myxh.qqzone.pojo.HostReply;
import com.myxh.qqzone.service.HostReplyService;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public class HostReplyServiceImpl implements HostReplyService
{
    private final HostReplyDao hostReplyDao = null;

    /**
     * 根据 reply 的 id 获取关联的所有的主人回复
     */
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId)
    {
        HostReply hostReplyByReplyId = hostReplyDao.getHostReplyByReplyId(replyId);

        return hostReplyByReplyId;
    }

    /**
     * 根据 hostReplyId 删除指定的主人回复
     */
    @Override
    public void deleteHostReplyByHostReplyId(Integer hostReplyId)
    {
        hostReplyDao.deleteHostReplyByHostReplyId(hostReplyId);
    }
}
