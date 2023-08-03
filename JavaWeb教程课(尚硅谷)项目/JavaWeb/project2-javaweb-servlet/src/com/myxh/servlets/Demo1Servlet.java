package com.myxh.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/5/25
 * @Description 演示 Servlet 的 service(ServletRequest var1, ServletResponse var2) 方法
 *              默认调用 doGet(HttpServletRequest req, HttpServletResponse resp) 方法, 是 Get 请求
 */
public class Demo1Servlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        super.doPost(request, response);
    }
}
