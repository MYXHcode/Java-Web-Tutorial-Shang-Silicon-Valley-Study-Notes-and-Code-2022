package com.myxh.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/7/8
 */
@WebFilter("*.do")
public class Filter2 implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        System.out.println("B1");

        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("B2");
    }

    @Override
    public void destroy()
    {
        Filter.super.destroy();
    }
}
