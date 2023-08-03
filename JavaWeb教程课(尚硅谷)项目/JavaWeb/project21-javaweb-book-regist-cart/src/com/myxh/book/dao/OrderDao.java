package com.myxh.book.dao;

import com.myxh.book.pojo.Order;
import com.myxh.book.pojo.User;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public interface OrderDao
{
    /**
     * 添加订单
     */
    void addOrder(Order order);

    /**
     * 获取指定用户的订单列表
     */
    List<Order> getOrderList(User user);

    /**
     * 获取订单总图书数数量
     */
    Integer getOrderTotalBookCount(Order order);
}
