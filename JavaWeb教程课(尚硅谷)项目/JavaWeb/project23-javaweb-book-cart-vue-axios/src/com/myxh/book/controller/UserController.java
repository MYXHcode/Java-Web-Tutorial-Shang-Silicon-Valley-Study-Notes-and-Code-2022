package com.myxh.book.controller;

import com.myxh.book.pojo.Cart;
import com.myxh.book.pojo.User;
import com.myxh.book.service.CartItemService;
import com.myxh.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MYXH
 * @date 2023/8/2
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

    public String regist(String name, String password, String email, String verifyCode, HttpSession session, HttpServletResponse response) throws IOException
    {
        Object kaptchaCodeObj = session.getAttribute("KAPTCHA_SESSION_KEY");

        if ((kaptchaCodeObj == null) || (!verifyCode.equals(kaptchaCodeObj)))
        {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script language='javascript'>alert('验证码错误！');</script>");

            return "user/regist";
        }
        else
        {
            if (verifyCode.equals(kaptchaCodeObj))
            {
                userService.regist(new User(name, password, email, 0));

                return "user/login";
            }
        }

        return "user/login";
    }

    public String checkName(String name)
    {
        User user = userService.getUser(name);

        if (user != null)
        {
            // 用户名已经被占用, 不可以注册
            return "json:{'name':'1'}";
            // return "ajax:1";
        }
        else
        {
            // 用户名未被占用, 可以注册
            return "json:{'name':'0'}";
            // return "ajax:0";
        }
    }
}
