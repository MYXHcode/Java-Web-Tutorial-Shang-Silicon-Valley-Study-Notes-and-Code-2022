package com.myxh.dome;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MYXH
 * @date 2023/6/24
 * @Description 演示 session 保存作用域(demo3 和 demo4)
 */
@WebServlet("/dome4")
public class Dome4Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        // 1. 获取 session 保存作用域保存的数据, key 为 name
        Object nameObj = request.getSession().getAttribute("name");
        System.out.println("nameObj = " + nameObj);
    }
}
