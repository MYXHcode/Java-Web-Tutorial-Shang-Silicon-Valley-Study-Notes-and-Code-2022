package com.myxh.book.controller;

import com.myxh.book.pojo.Cart;
import com.myxh.book.pojo.User;
import com.myxh.book.service.CartItemService;
import com.myxh.book.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @author MYXH
 * @date 2023/7/28
 */
public class UserController
{
    private UserService userService;
    private CartItemService cartItemService;

    public String login(String name, String password, HttpSession session)
    {
        User user = userService.login(name, password);

        if (user != null)
        {
            Cart cart = cartItemService.getCart(user);

            user.setCart(cart);

            session.setAttribute("currentUser", user);

            return "redirect:book.do";
        }

        return "user/login";
    }
}
