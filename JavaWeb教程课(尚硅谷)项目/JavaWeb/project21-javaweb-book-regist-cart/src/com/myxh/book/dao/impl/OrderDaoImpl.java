package com.myxh.book.dao.impl;

import com.myxh.book.dao.OrderDao;
import com.myxh.book.pojo.Order;
import com.myxh.book.pojo.User;
import com.myxh.ssm.basedao.BaseDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao
{
    /**
     * 添加订单
     */
    @Override
    public void addOrder(Order order)
    {
        String sql = "insert into t_order values(0, ?, ?, ?, ?, ?)";

        // 此处为什么需要接受 executeUpdate 的返回值, 然后设置到 order 中的 id 属性上? 因为需要多表关联操作
        int orderId = super.executeUpdate(sql, order.getOrderNo(), order.getOrderDate(), order.getOrderUser().getId(), order.getOrderMoney(), order.getOrderStatus());

        order.setId(orderId);
    }

    /**
     * 获取指定用户的订单列表
     */
    @Override
    public List<Order> getOrderList(User user)
    {
        String sql = "select * from t_order where orderUser = ?";

        return executeQuery(sql, user.getId());
    }

    /**
     * 获取订单总图书数数量
     */
    @Override
    public Integer getOrderTotalBookCount(Order order)
    {
        String sql = """
                 select sum(t3.buyCount) as totalBookCount, t3.order
                   from (select t1.id, t2.buyCount, t2.order
                      from t_order t1
                               inner join t_order_item t2
                                          on t1.id = t2.order
                      where t1.orderUser = ?) t3
                where t3.order = ?
                group by t3.order;""";

        return ((BigDecimal) executeComplexQuery(sql, order.getOrderUser().getId(), order.getId())[0]).intValue();
    }
}
