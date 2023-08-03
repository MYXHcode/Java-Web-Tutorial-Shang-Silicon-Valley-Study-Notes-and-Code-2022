package com.myxh.qqzone.pojo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/17
 */
public class Topic
{
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime topicDate;

    // Topic : UserBasic -> M : 1
    private UserBasic author;

    // Topic : Reply -> 1 : N
    private List<Reply> replyList;

    public Topic()
    {

    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public LocalDateTime  getTopicDate()
    {
        return topicDate;
    }

    public void setTopicDate(LocalDateTime  topicDate)
    {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor()
    {
        return author;
    }

    public void setAuthor(UserBasic author)
    {
        this.author = author;
    }

    public List<Reply> getReplyList()
    {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList)
    {
        this.replyList = replyList;
    }
}
