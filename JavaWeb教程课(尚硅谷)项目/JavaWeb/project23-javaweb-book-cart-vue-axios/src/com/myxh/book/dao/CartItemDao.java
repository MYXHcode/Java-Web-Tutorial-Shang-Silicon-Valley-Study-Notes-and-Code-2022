package com.myxh.book.dao;

import com.myxh.book.pojo.CartItem;
import com.myxh.book.pojo.User;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public interface CartItemDao
{
    /**
     * 新增购物车项
     */
    void addCartItem(CartItem cartItem);

    /**
     * 修改特定的购物车项
     */
    void updateCartItem(CartItem cartItem);

    /**
     * 获取特定用户的所有购物车项
     */
    List<CartItem> getCartItemList(User user);

    /**
     * 删除指定的购物车项
     */
    void deleteCartItem(CartItem cartItem);
}
