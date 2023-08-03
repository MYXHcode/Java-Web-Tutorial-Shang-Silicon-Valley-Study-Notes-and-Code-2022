package com.myxh.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/7/29
 */
@WebServlet("/cookie1")
public class CookieServlet1 extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 1. 创建一个 Cookie 对象
        Cookie cookie = new Cookie("name", "MYXH");

        // 2. 将这个 Cookie 对象保存到浏览器端
        response.addCookie(cookie);

        // 3. 服务器端内部转发
        request.getRequestDispatcher("cookie_servlet1.html").forward(request, response);
    }
}
