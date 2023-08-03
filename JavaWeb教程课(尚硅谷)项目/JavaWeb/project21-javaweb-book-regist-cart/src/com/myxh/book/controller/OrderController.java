package com.myxh.book.controller;

import com.myxh.book.pojo.Order;
import com.myxh.book.pojo.User;
import com.myxh.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public class OrderController
{
    private OrderService orderService;

    /**
     * 结账
     */
    public String checkout(HttpSession session)
    {

        Order order = new Order();

        LocalDateTime now = LocalDateTime.now();

        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        order.setOrderNo(UUID.randomUUID().toString() + "_" + year + month + day + hour + minute + second);
        order.setOrderDate(now);

        User user = (User) session.getAttribute("currentUser");

        order.setOrderUser(user);
        order.setOrderMoney(user.getCart().getTotalMoney());
        order.setOrderStatus(0);

        orderService.addOrder(order);

        return "index";
    }

    /**
     * 查看订单列表
     */
    public String getOrderList(HttpSession session)
    {
        User user = (User) session.getAttribute("currentUser");

        List<Order> orderList = orderService.getOrderList(user);

        user.setOrderList(orderList);

        session.setAttribute("currentUser", user);

        return "order/order";
    }
}
