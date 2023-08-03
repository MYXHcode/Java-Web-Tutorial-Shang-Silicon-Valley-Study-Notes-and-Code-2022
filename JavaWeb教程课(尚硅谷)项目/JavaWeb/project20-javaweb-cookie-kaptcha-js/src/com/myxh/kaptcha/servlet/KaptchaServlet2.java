package com.myxh.kaptcha.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author MYXH
 * @date 2023/7/29
 */
@WebServlet("/kaptcha1")
public class KaptchaServlet2 extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();

        Object kaptchaSessionKey = session.getAttribute("KAPTCHA_SESSION_KEY");

        System.out.println("kaptchaSessionKey = " + kaptchaSessionKey);
    }
}
