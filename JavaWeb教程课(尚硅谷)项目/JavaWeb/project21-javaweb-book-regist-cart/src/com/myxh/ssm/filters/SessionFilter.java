package com.myxh.ssm.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author MYXH
 * @date 2023/7/30
 */
/*
@WebFilter(urlPatterns = {"*.do", "*.html"},
        initParams = {
                @WebInitParam(
                        name = "whiteList",
                        value = "/project21_javaweb_book_regist_cart/page.do?operate=page&page=user/login," +
                                "/project21_javaweb_book_regist_cart/user.do?null," +
                                "/project21_javaweb_book_regist_cart/page.do?operate=page&page=user/regist"
                )
        }
)
*/
public class SessionFilter implements Filter
{
    List<String> whiteList = null;

    @Override
    public void init(FilterConfig filterConfig)
    {
        String white = filterConfig.getInitParameter("whiteList");

        String[] whiteArr = white.split(",");

        whiteList = Arrays.asList(whiteArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // http://localhost:8080/project21_javaweb_book_regist_cart/page.do?operate=page&page=user/login
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = uri + "?" + queryString;

        if (queryString.contains("checkName") || queryString.contains("login") || queryString.contains("regist"))
        {
            // 检查是否是 checkName、login 或 regist 请求
            filterChain.doFilter(request, response);
        }
        else if (whiteList.contains(str))
        {
            // 检查白名单
            filterChain.doFilter(request, response);
        }
        else
        {
            // 其他请求, 验证 session
            HttpSession session = request.getSession();

            Object currentUserObj = session.getAttribute("currentUser");

            if (currentUserObj == null)
            {
                response.sendRedirect("page.do?operate=page&page=user/login");
            }
            else
            {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy()
    {
        Filter.super.destroy();
    }
}
