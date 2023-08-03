package com.myxh.book.controller;

import com.google.gson.Gson;
import com.myxh.book.pojo.Book;
import com.myxh.book.pojo.Cart;
import com.myxh.book.pojo.CartItem;
import com.myxh.book.pojo.User;
import com.myxh.book.service.CartItemService;

import javax.servlet.http.HttpSession;

/**
 * @author MYXH
 * @date 2023/8/2
 */
public class CartController
{
    private CartItemService cartItemService;

    /**
     * 加载当前用户的购物车信息(Thymeleaf 页面调用此方法)
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

    /**
     * 编辑当前用户的购物车中(Thymeleaf 页面调用此方法)
     */
    public String editCart(Integer cartItemId, Integer buyCount)
    {
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));

        return "redirect:cart.do";
    }

    /**
     * 加载当前用户的购物车信息(Vue 页面调用的方法)
     */
    public String cartInfo(HttpSession session)
    {
        User user = (User) session.getAttribute("currentUser");

        Cart cart = cartItemService.getCart(user);

        /*
        调用 Cart 中的三个属性的 get 方法, 目的是在此处计算这三个属性的值, 否刚这三个属性为 nu11,
        导致的结果就是下一步的 gson 转化时为 nul1 的属性会被忽略
         */
        cart.getTotalBookCount();
        cart.getTotalCount();
        cart.getTotalMoney();

        Gson gson = new Gson();

        /*
        打开 java.time 模块以允许 Gson 访问私有字段,
        --add-opens java.base/java.time=ALL-UNNAMED
        需要在运行 Java 程序时添加上面这个 JVM 参数, 打开 java.time 模块并允许未命名模块 (Gson) 访问其私有成员
         */
        String cartJsonStr = gson.toJson(cart);

        return "json:" + cartJsonStr;
    }

    /**
     * 编辑当前用户的购物车中(Vue 页面调用此方法)
     */
    public String editCartInfo(Integer cartItemId, Integer buyCount)
    {
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));

        return "";
    }
}
