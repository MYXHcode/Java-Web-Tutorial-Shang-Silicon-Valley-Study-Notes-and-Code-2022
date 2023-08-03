package com.myxh.dome;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/6/24
 * @Description 演示 request 保存作用域(demo1 和 demo2)
 */
@WebServlet("/dome1")
public class Dome1Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // 1. 向 request 保存作用域保存数据
        request.setAttribute("name", "MYXH");

        // 2. 客户端重定向
        // response.sendRedirect("dome2");

        // 3. 服务器端转发
        request.getRequestDispatcher("dome2").forward(request, response);
    }
}
