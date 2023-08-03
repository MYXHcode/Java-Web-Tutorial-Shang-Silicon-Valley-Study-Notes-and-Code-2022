package com.myxh.ssm.filters;

import com.myxh.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/7/19
 */
@WebFilter(urlPatterns = {"*.do"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8")
        }
)
public class CharacterEncodingFilter implements Filter
{
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig)
    {
        String encodingStr = filterConfig.getInitParameter("encoding");

        if (StringUtils.isNotEmpty(encodingStr))
        {
            encoding = encodingStr;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        ((HttpServletRequest) servletRequest).setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy()
    {
        Filter.super.destroy();
    }
}
