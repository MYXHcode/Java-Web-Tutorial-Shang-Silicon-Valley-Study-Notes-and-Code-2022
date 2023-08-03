package com.myxh.dome;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MYXH
 * @date 2023/6/24
 * @Description 演示 application 保存作用域(demo5 和 demo6)
 */
@WebServlet("/dome6")
public class Dome6Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        // 1. 获取 application 保存作用域保存的数据, key 为 name
        ServletContext application = request.getServletContext();
        Object nameObj = application.getAttribute("name");
        System.out.println("nameObj = " + nameObj);
    }
}
