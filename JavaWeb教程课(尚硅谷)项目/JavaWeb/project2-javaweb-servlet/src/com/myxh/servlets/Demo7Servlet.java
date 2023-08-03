package com.myxh.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MYXH
 * @date 2023/5/25
 * @Description 演示服务器端内部转发以及客户端重定向
 */
public class Demo7Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("Demo7Servlet...");
    }
}
