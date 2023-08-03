package com.myxh.qqzone.pojo;

import java.time.LocalDateTime;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public class HostReply
{
    private Integer id;
    private String content;
    private LocalDateTime hostReplyDate;

    // HostReply : UserBasic -> M : 1
    private UserBasic author;

    // HostReply : Reply -> 1 : 1
    private Reply reply;

    public HostReply()
    {

    }

    public HostReply(Integer id)
    {
        this.id = id;
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

    public LocalDateTime  getHostReplyDate()
    {
        return hostReplyDate;
    }

    public void setHostReplyDate(LocalDateTime  hostReplyDate)
    {
        this.hostReplyDate = hostReplyDate;
    }

    public UserBasic getAuthor()
    {
        return author;
    }

    public void setAuthor(UserBasic author)
    {
        this.author = author;
    }

    public Reply getReply()
    {
        return reply;
    }

    public void setReply(Reply reply)
    {
        this.reply = reply;
    }
}
