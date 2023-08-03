package com.myxh.book.controller;

import com.myxh.book.pojo.Book;
import com.myxh.book.pojo.Cart;
import com.myxh.book.pojo.CartItem;
import com.myxh.book.pojo.User;
import com.myxh.book.service.CartItemService;

import javax.servlet.http.HttpSession;

/**
 * @author MYXH
 * @date 2023/7/30
 */
public class CartController
{
    private CartItemService cartItemService;

    /**
     * 加载当前用户的购物车信息
     */
    public String index(HttpSession session)
    {
        User user = (User) session.getAttribute("currentUser");

        Cart cart = cartItemService.getCart(user);

        user.setCart(cart);

        session.setAttribute("currentUser", user);

        return "cart/cart";
    }

    /**
     * 将指定的图书添加到当前用户的购物车中
     */
    public String addCart(Integer bookId, HttpSession session)
    {
        User user = (User) session.getAttribute("currentUser");

        CartItem cartItem = new CartItem(new Book(bookId), 1, user);

        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());

        return "redirect:cart.do";
    }

    public String editCart(Integer cartItemId, Integer buyCount)
    {
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));

        return "redirect:cart.do";
    }
}
