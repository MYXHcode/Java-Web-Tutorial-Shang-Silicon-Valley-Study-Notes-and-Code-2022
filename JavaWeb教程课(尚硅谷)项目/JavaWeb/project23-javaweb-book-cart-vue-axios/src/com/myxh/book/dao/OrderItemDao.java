package com.myxh.book.dao;

import com.myxh.book.pojo.OrderItem;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public interface OrderItemDao
{
    /**
     * 添加订单项
     */
    void addOrderItem(OrderItem orderItem);
}
