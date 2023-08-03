package com.myxh.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MYXH
 * @date 2023/5/25
 * @Description 演示从 HttpSession 保存的作用域中获取数据
 */
public class Demo5Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        Object nameObj = request.getSession().getAttribute("name");
        System.out.println(nameObj);
    }
}
