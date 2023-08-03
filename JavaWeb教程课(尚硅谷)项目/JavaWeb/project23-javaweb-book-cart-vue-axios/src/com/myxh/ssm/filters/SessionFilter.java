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
 * @date 2023/8/2
 */
/*
@WebFilter(urlPatterns = {"*.do", "*.html"},
        initParams = {
                @WebInitParam(
                        name = "whiteList",
                        value = "/project23_javaweb_book_cart_vue_axios/page.do?operate=page&page=user/login," +
                                "/project23_javaweb_book_cart_vue_axios/user.do?null," +
                                "/project23_javaweb_book_cart_vue_axios/page.do?operate=page&page=user/regist"
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
        // 初始化白名单
        String white = filterConfig.getInitParameter("whiteList");

        String[] whiteArr = white.split(",");

        whiteList = Arrays.asList(whiteArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // http://localhost:8080/project23_javaweb_book_cart_vue_axios/page.do?operate=page&page=user/login
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str;

        if (queryString == null)
        {
            str = uri;
        }
        else
        {
            str = uri + "?" + queryString;
        }

        // 检查白名单
        if (whiteList.contains(str))
        {
            filterChain.doFilter(request, response);

            return;
        }

        if (queryString != null)
        {
            // 检查特定请求
            if (queryString.contains("checkName") || queryString.contains("login") || queryString.contains("regist"))
            {
                filterChain.doFilter(request, response);

                return;
            }
        }

        // 检查重定向状态码
        if (response.getStatus() == 302)
        {
            filterChain.doFilter(request, response);

            return;
        }

        // 检查异步请求
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))
        {
            filterChain.doFilter(request, response);

            return;
        }

        // 其他请求, 验证 session
        HttpSession session = request.getSession();

        Object currentUserObj = session.getAttribute("currentUser");

        if ((queryString != null) && (!queryString.contains("login")))
        {
            if (currentUserObj == null)
            {
                response.sendRedirect("page.do?operate=page&page=user/login");

                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy()
    {
        // 销毁
        Filter.super.destroy();
    }
}
