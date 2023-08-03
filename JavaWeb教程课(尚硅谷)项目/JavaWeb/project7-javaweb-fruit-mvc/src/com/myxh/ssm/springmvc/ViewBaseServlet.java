package com.myxh.ssm.springmvc;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MYXH
 * @date 2023/6/29
 */
public class ViewBaseServlet extends HttpServlet
{
    private TemplateEngine templateEngine;

    @Override
    public void init()
    {
        // 1. 获取 ServletContext 对象
        ServletContext servletContext = this.getServletContext();

        // 2. 创建 Thymeleaf 解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        // 3. 给解析器对象设置参数
        // ① HTML 是默认模式, 明确设置是为了代码更容易理解
        templateResolver.setTemplateMode(TemplateMode.HTML);

        // ② 设置前缀
        String viewPrefix = servletContext.getInitParameter("view-prefix");

        templateResolver.setPrefix(viewPrefix);

        // ③ 设置后缀
        String viewSuffix = servletContext.getInitParameter("view-suffix");

        templateResolver.setSuffix(viewSuffix);

        // ④ 设置缓存过期时间(毫秒)
        templateResolver.setCacheTTLMs(60000L);

        // ⑤ 设置是否缓存
        templateResolver.setCacheable(true);

        // ⑥ 设置服务器端编码方式
        templateResolver.setCharacterEncoding("utf-8");

        // 4. 创建模板引擎对象
        templateEngine = new TemplateEngine();

        // 5. 给模板引擎对象设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);
    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        // 1. 设置响应体内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");

        // 2. 创建 WebContext 对象
        WebContext webContext = new WebContext(req, resp, getServletContext());

        // 3. 处理模板数据
        templateEngine.process(templateName, webContext, resp.getWriter());
    }
}
