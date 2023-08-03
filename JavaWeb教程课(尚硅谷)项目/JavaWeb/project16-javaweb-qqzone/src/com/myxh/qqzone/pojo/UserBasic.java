package com.myxh.qqzone.pojo;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/19
 */
public class UserBasic
{
    private Integer id;
    private String loginId;
    private String nickName;
    private String password;
    private String headImage;

    // UserBasic : UserDetail -> 1 : 1
    private UserDetail userDetail;

    // UserBasic : Topic -> 1 : N
    private List<Topic> topicList;

    // UserBasic : UserBasic -> M : N
    private List<UserBasic> friendList;

    public UserBasic()
    {

    }

    public UserBasic(Integer id)
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

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getHeadImage()
    {
        return headImage;
    }

    public void setHeadImage(String headImage)
    {
        this.headImage = headImage;
    }

    public UserDetail getUserDetail()
    {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail)
    {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList()
    {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList)
    {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList()
    {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList)
    {
        this.friendList = friendList;
    }
}
