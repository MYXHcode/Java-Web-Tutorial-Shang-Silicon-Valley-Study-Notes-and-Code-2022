package com.myxh.qqzone.dao.impl;

import com.myxh.qqzone.dao.TopicDao;
import com.myxh.qqzone.pojo.Topic;
import com.myxh.qqzone.pojo.UserBasic;
import com.myxh.ssm.basedao.BaseDao;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/17
 */
public class TopicDaoImpl extends BaseDao<Topic> implements TopicDao
{
    /**
     * 获取指定用户的日志列表
     */
    @Override
    public List<Topic> getTopicList(UserBasic userBasic)
    {
        String sql = "select * from t_topic where author = ?";

        return super.executeQuery(sql, userBasic.getId());
    }

    /**
     * 添加加日志
     */
    @Override
    public void addTopic(Topic topic)
    {

    }

    /**
     * 删除日志
     */
    @Override
    public void deleteTopic(Topic topic)
    {

    }

    /**
     * 获取特定日志信息
     */
    @Override
    public Topic getTopic(Integer id)
    {
        return null;
    }
}
