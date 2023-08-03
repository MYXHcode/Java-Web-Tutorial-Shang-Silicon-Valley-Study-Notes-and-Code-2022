package com.myxh.book.service;

import com.myxh.book.pojo.Cart;
import com.myxh.book.pojo.CartItem;
import com.myxh.book.pojo.User;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/28
 */
public interface CartItemService
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
     * 新增购物车项或者修改特定的购物车项
     */
    void addOrUpdateCartItem(CartItem cartItem , Cart cart);

    /**
     * 加裁特定用户的购物车信息
     */
    Cart getCart(User user);

    /**
     * 获取指定用户的所有购物车项列表(这个方法内部查询时, 会将 book 的详细信息设置进去
     */
    List<CartItem> getCartItemList(User user);
}
