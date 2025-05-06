package com.ruoyi.common.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import com.ruoyi.common.utils.StringUtils;

/**
 * 防止重复提交过滤器
 * 
 * @author ruoyi
 */
public class RepeatableFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        if (StringUtils.equalsIgnoreCase(req.getMethod(), "POST"))
        {
            RepeatedlyRequestWrapper repeatedlyRequest = new RepeatedlyRequestWrapper(req, response);
            chain.doFilter(repeatedlyRequest, response);
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy()
    {

    }
}
