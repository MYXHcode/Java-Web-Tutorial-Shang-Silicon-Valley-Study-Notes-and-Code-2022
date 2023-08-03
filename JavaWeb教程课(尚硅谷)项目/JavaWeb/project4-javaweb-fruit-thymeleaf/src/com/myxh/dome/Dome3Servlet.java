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
 * @Description 演示 session 保存作用域(demo3 和 demo4)
 */
@WebServlet("/dome3")
public class Dome3Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // 1. 向 session 保存作用域保存数据
        request.getSession().setAttribute("name", "MYXH");

        // 2. 客户端重定向
        response.sendRedirect("dome4");

        // 3. 服务器端转发
        // request.getRequestDispatcher("dome4").forward(request, response);
    }
}
