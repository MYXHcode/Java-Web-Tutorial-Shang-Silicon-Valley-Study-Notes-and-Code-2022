package com.myxh.book.service.impl;

import com.myxh.book.dao.CartItemDao;
import com.myxh.book.pojo.Book;
import com.myxh.book.pojo.Cart;
import com.myxh.book.pojo.CartItem;
import com.myxh.book.pojo.User;
import com.myxh.book.service.BookService;
import com.myxh.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MYXH
 * @date 2023/7/28
 */
public class CartItemServiceImpl implements CartItemService
{
    private CartItemDao cartItemDao;
    private BookService bookService;

    /**
     * 新增购物车项
     */
    @Override
    public void addCartItem(CartItem cartItem)
    {
        cartItemDao.addCartItem(cartItem);
    }

    /**
     * 修改特定的购物车项
     */
    @Override
    public void updateCartItem(CartItem cartItem)
    {
        cartItemDao.updateCartItem(cartItem);
    }

    /**
     * 新增购物车项或者修改特定的购物车项
     */
    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart)
    {

        //判断当前用户的购物车中是否有这本书的 cartItem, 有则更新, 无则添加
        if (cart != null)
        {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();

            if (cartItemMap == null)
            {
                cartItemMap = new HashMap<>();
            }

            if (cartItemMap.containsKey(cartItem.getBook().getId()))
            {
                // 1. 如果当前用户的购物车中已经存在这个图书, 那么将购物车中这本图书的数量 + 1
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());

                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);

                updateCartItem(cartItemTemp);
            }
            else
            {
                // 2. 否则, 在我的购物车中新增一个这本图书的 cartItem, 数量是 1
                addCartItem(cartItem);
            }
        }
        else
        {
            // 没有购物车的情况, 直接新增
            addCartItem(cartItem);
        }
    }

    /**
     * 加裁特定用户的购物车信息
     */
    @Override
    public Cart getCart(User user)
    {
        List<CartItem> cartItemList = getCartItemList(user);

        Map<Integer, CartItem> cartItemMap = new HashMap<>();

        for (CartItem cartItem : cartItemList)
        {
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }

        Cart cart = new Cart();

        cart.setCartItemMap(cartItemMap);

        return cart;
    }

    /**
     * 加裁特定用户的购物车项列表信息
     */
    @Override
    public List<CartItem> getCartItemList(User user)
    {
        List<CartItem> cartItemList = cartItemDao.getCartItemList(user);

        for (CartItem cartItem : cartItemList)
        {
            Book book = bookService.getBook(cartItem.getBook().getId());

            cartItem.setBook(book);
        }

        return cartItemList;
    }
}
