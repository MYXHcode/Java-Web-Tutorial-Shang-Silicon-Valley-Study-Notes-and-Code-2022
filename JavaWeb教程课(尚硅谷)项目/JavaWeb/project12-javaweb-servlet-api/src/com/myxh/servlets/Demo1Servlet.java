package com.myxh.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MYXH
 * @date 2023/7/7
 * @Description 演示 Servlet 的生命周期: 实例化、初始化、服务、销毁
 */
/*
@WebServlet(urlPatterns = {"/demo1_servlet"},
        initParams = {
                @WebInitParam(name = "Hello", value = "World"),
                @WebInitParam(name = "name", value = "MYXH"),
        }
)
 */
public class Demo1Servlet extends HttpServlet
{
    @Override
    public void init()
    {
        ServletConfig servletConfig = getServletConfig();
        String initValue = servletConfig.getInitParameter("Hello");
        System.out.println("initValue = " + initValue);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = " + contextConfigLocation);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        ServletContext servletContext1 = request.getServletContext();
        String contextConfigLocation1 = servletContext1.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation1 = " + contextConfigLocation1);

        ServletContext servletContext2 = request.getSession().getServletContext();
        String contextConfigLocation2 = servletContext2.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation2 = " + contextConfigLocation2);
    }
}
