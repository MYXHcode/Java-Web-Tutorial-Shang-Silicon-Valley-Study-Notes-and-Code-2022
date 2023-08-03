package com.myxh.book.service;

import com.myxh.book.pojo.Order;
import com.myxh.book.pojo.User;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public interface OrderService
{
    /**
     * 添加订单
     */
    void addOrder(Order order);

    /**
     * 获取指定用户的订单列表
     */
    List<Order> getOrderList(User user);
}
