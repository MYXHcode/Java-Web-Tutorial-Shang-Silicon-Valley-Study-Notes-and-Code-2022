package com.myxh.dome;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MYXH
 * @date 2023/6/24
 * @Description 演示 request 保存作用域(demo1 和 demo2)
 */
@WebServlet("/dome2")
public class Dome2Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        // 1. 获取 request 保存作用域保存的数据, key 为 name
        Object nameObj = request.getAttribute("name");
        System.out.println("nameObj = " + nameObj);
    }
}
