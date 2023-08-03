package com.myxh.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author MYXH
 * @date 2023/5/25
 * @Description 演示 Servlet 的 service(HttpServletRequest req, HttpServletResponse resp) 方法的 Session
 */
public class Demo3Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        // 获取 session, 如果获取不到, 则创建一个新的
        HttpSession session = request.getSession();
        System.out.println("session ID : " + session.getId());
    }
}
