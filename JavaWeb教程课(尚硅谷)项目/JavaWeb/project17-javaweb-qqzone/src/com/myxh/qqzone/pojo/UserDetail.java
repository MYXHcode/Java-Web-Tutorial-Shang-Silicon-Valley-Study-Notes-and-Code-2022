package com.myxh.qqzone.pojo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author MYXH
 * @date 2023/7/21
 */
public class UserDetail
{
    private Integer id;
    private String realName;
    private String tel;
    private String email;

    /*
    父类: java.util.Date 年月日时分秒毫秒
    子类: java.sql.Date 年月日
    子类: java.sql.Time 时分秒
     */
    private LocalDateTime birth;
    private String star;

    public UserDetail()
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

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDateTime  getBirth()
    {
        return birth;
    }

    public void setBirth(LocalDateTime  birth)
    {
        this.birth = birth;
    }

    public String getStar()
    {
        return star;
    }

    public void setStar(String star)
    {
        this.star = star;
    }

    public Date getBirthAsDate()
    {
        return Timestamp.valueOf(birth);
    }
}
