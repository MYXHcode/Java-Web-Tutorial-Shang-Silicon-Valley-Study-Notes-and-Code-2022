package com.myxh.book.service.impl;

import com.myxh.book.dao.CartItemDao;
import com.myxh.book.dao.OrderDao;
import com.myxh.book.dao.OrderItemDao;
import com.myxh.book.pojo.CartItem;
import com.myxh.book.pojo.Order;
import com.myxh.book.pojo.OrderItem;
import com.myxh.book.pojo.User;
import com.myxh.book.service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public class OrderServiceImpl implements OrderService
{
    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    private CartItemDao cartItemDao;

    /**
     * 添加订单
     */
    @Override
    public void addOrder(Order order)
    {
        // 1. 订单表添加 1 条记录
        // 执行完这一步, order 中的 id 是有值的
        orderDao.addOrder(order);

        // 2. 订单项表添加对应的多条记录
        // order 中的 orderItemList 是空的, 此处应该根据用户的购物车中的购物车项去转换成一条一条的订单项
        User currentUser = order.getOrderUser();

        Map<Integer, CartItem> cartItemMap = currentUser.getCart().getCartItemMap();

        for (CartItem cartItem : cartItemMap.values())
        {
            OrderItem orderItem = new OrderItem();

            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrder(order);

            orderItemDao.addOrderItem(orderItem);
        }

        // 3. 购物车项表中需要删除对应的多条记录
        for (CartItem cartItem : cartItemMap.values())
        {
            cartItemDao.deleteCartItem(cartItem);
        }
    }

    /**
     * 获取指定用户的订单列表
     */
    @Override
    public List<Order> getOrderList(User user)
    {
        List<Order> orderList = orderDao.getOrderList(user);

        for (Order order : orderList)
        {
            Integer totalBookCount = orderDao.getOrderTotalBookCount(order);
            order.setTotalBookCount(totalBookCount);
        }

        return orderList;
    }
}
