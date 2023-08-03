package com.myxh.qqzone.dao.impl;

import com.myxh.qqzone.dao.HostReplyDao;
import com.myxh.qqzone.pojo.HostReply;
import com.myxh.ssm.basedao.BaseDao;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public class HostReplyDaoImpl extends BaseDao<HostReply> implements HostReplyDao
{
    /**
     * 根据 replyId 查询关联的 HostReply 实体
     */
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId)
    {
        String sql = "select * from t_host_reply where reply = ?";

        return load(sql, replyId);
    }

    /**
     * 删除特定的 HostReply
     */
    @Override
    public void deleteHostReplyByHostReplyId(Integer hostReplyId)
    {
        String sql = "delete from t_host_reply where id = ?";

        super.executeUpdate(sql, hostReplyId);
    }
}
