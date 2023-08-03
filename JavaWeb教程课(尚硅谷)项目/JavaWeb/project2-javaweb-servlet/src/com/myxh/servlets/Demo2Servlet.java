package com.myxh.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MYXH
 * @date 2023/5/25
 * @Description 演示 Servlet 的生命周期
 */
public class Demo2Servlet extends HttpServlet
{
    public Demo2Servlet()
    {
        System.out.println("正在实例化...");
    }

    @Override
    public void init()
    {
        System.out.println("正在初始化...");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    {
        System.out.println("正在服务...");
    }

    @Override
    public void destroy()
    {
        System.out.println("正在销毁...");
    }
}
