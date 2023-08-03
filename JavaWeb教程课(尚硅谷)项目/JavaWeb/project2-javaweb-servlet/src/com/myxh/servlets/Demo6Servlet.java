package com.myxh.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/5/25
 * @Description 演示服务器端内部转发以及客户端重定向
 */
public class Demo6Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Demo6Servlet...");

        // 服务器端内部转发
        // request.getRequestDispatcher("demo7_servlet").forward(request, response);

        // 客户端重定向
        response.sendRedirect("demo7_servlet");
    }
}
