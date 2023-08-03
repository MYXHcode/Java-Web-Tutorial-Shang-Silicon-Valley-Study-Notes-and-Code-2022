package com.myxh.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MYXH
 * @date 2023/5/25
 * @Description 演示向 HttpSession 保存数据
 */
public class Demo4Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().setAttribute("name", "MYXH");
    }
}
