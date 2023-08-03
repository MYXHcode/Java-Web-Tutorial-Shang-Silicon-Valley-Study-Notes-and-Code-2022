package com.myxh.book.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public class Order
{
    private Integer id;
    private String orderNo;

    private LocalDateTime orderDate;
    private User orderUser;
    private BigDecimal orderMoney;
    private Integer orderStatus;

    // Order : OrderItem -> 1 : N
    private List<OrderItem> orderItemList;
    private Integer totalBookCount;

    public Order()
    {

    }

    public Order(Integer id)
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

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public LocalDateTime getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate)
    {
        this.orderDate = orderDate;
    }

    public User getOrderUser()
    {
        return orderUser;
    }

    public void setOrderUser(User orderUser)
    {
        this.orderUser = orderUser;
    }

    public BigDecimal getOrderMoney()
    {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney)
    {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItemList()
    {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList)
    {
        this.orderItemList = orderItemList;
    }

    public Integer getTotalBookCount()
    {
        return totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount)
    {
        this.totalBookCount = totalBookCount;
    }

    public Date getOrderDateAsDate()
    {
        return Timestamp.valueOf(orderDate);
    }
}
