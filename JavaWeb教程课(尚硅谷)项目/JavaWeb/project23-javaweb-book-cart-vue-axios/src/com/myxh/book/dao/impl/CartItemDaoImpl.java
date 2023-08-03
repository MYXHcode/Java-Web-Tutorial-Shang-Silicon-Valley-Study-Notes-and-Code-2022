package com.myxh.book.dao.impl;

import com.myxh.book.dao.CartItemDao;
import com.myxh.book.pojo.CartItem;
import com.myxh.book.pojo.User;
import com.myxh.ssm.basedao.BaseDao;

import java.util.List;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public class CartItemDaoImpl extends BaseDao<CartItem> implements CartItemDao
{
    /**
     * 新增购物车项
     */
    @Override
    public void addCartItem(CartItem cartItem)
    {
        String sql="insert into t_cart_item values(0, ?, ?, ?)";

        executeUpdate(sql, cartItem.getBook().getId(), cartItem.getBuyCount(), cartItem.getUser().getId());
    }

    /**
     * 修改特定的购物车项
     */
    @Override
    public void updateCartItem(CartItem cartItem)
    {
        String sql="update t_cart_item set buyCount = ? where id = ?";

        executeUpdate(sql,cartItem.getBuyCount(), cartItem.getId());
    }

    /**
     * 获取特定用户的所有购物车项
     */
    @Override
    public List<CartItem> getCartItemList(User user)
    {
        String sql = "select * from t_cart_item where user = ?";

        return executeQuery(sql,user.getId());
    }

    /**
     * 删除指定的购物车项
     */
    @Override
    public void deleteCartItem(CartItem cartItem)
    {
        String sql = "delete from t_cart_item where id = ?";

        super.executeUpdate(sql, cartItem.getId());
    }
}
