package com.myxh.book.dao.impl;

import com.myxh.book.dao.OrderItemDao;
import com.myxh.book.pojo.OrderItem;
import com.myxh.ssm.basedao.BaseDao;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao
{
    /**
     * 添加订单项
     */
    @Override
    public void addOrderItem(OrderItem orderItem)
    {
        String sql = "insert into t_order_item values(0, ?, ?, ?)";

        super.executeUpdate(sql, orderItem.getBook().getId(), orderItem.getBuyCount(), orderItem.getOrder().getId());
    }
}
