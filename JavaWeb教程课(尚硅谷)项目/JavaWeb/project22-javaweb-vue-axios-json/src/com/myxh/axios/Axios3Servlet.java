package com.myxh.axios;

import com.google.gson.Gson;
import com.myxh.pojo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/8/1
 */
@WebServlet("/axios3.do")
public class Axios3Servlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        BufferedReader bufferedReader = request.getReader();

        StringBuilder stringBuilder = new StringBuilder();

        String str;

        while ((str = bufferedReader.readLine()) != null)
        {
            stringBuilder.append(str);
        }

        str = stringBuilder.toString();

        // 已得到 Java String, 需要转化成 Java Object
        Gson gson = new Gson();

        /*
        Gson 有两个API:
        1. fromJson(string, T) 将 JSON 字符串转化成 Java Objet
        2. toJson(Java Object) 将 Java Object 转化成 JSON 字符串, 这样才能响应给客户端
         */
        User user = gson.fromJson(str, User.class);
        user.setName("末影小黑xh");
        user.setPassword("520.ILY!");

        // 假设 user 是从数据库查询出来的, 现在需要将其转化成 JSON 格式的字符串, 然后响应客户端
        String userJsonStr = gson.toJson(user);

        response.setCharacterEncoding("UTF-8");

        // MINE-TYPE
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().write(userJsonStr);
    }
}
