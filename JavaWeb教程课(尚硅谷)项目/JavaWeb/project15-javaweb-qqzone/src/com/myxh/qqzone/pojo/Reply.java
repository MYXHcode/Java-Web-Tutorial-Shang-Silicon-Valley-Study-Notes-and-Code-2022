package com.myxh.qqzone.pojo;

import java.time.LocalDateTime;

/**
 * @author MYXH
 * @date 2023/7/17
 */
public class Reply
{
    private Integer id;
    private String content;
    private LocalDateTime replyDate;

    // Reply : UserBasic -> M : 1
    private UserBasic author;

    // Reply : Topic -> M : 1
    private Topic topic;

    // Reply : HostReply -> 1 : 1
    private HostReply hostReply;

    public Reply()
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

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public LocalDateTime  getReplyDate()
    {
        return replyDate;
    }

    public void setReplyDate(LocalDateTime  replyDate)
    {
        this.replyDate = replyDate;
    }

    public UserBasic getAuthor()
    {
        return author;
    }

    public void setAuthor(UserBasic author)
    {
        this.author = author;
    }

    public Topic getTopic()
    {
        return topic;
    }

    public void setTopic(Topic topic)
    {
        this.topic = topic;
    }

    public HostReply getHostReply()
    {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply)
    {
        this.hostReply = hostReply;
    }
}
