package com.myxh.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/7/8
 */
@WebServlet("/demo1_servlet.do")
public class Demo1Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("dome1 service...");
        request.getRequestDispatcher("hello_world.html").forward(request, response);
    }
}
