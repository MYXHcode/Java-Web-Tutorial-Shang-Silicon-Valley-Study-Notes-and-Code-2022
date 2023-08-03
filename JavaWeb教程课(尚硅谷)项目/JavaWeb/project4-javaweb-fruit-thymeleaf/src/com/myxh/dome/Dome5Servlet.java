package com.myxh.dome;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/6/24
 * @Description 演示 application 保存作用域(demo5 和 demo6)
 */
@WebServlet("/dome5")
public class Dome5Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // 1. 向 application 保存作用域保存数据
        // ServletContext: Servlet 上下文
        ServletContext application = request.getServletContext();
        application.setAttribute("name", "MYXH");

        // 2. 客户端重定向
        response.sendRedirect("dome6");

        // 3. 服务器端转发
        // request.getRequestDispatcher("dome6").forward(request, response);
    }
}
