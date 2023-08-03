package com.myxh.axios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MYXH
 * @date 2023/8/1
 */
@WebServlet("/axios1.do")
public class Axios1Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        System.out.println("name = " + name);
        System.out.println("password = " + password);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.write(name + "_" + password);

        throw new NullPointerException("这里故意抛出一个空指针异常...");
    }
}
