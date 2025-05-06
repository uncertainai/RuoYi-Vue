package com.ruoyi.framework.security.filter;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;

/**
 * token过滤器 验证token有效性
 * 
 * @author ruoyi
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        // 获取请求的URI
        String requestURI = request.getRequestURI();
        
        // 检查是否是允许匿名访问的URL
        if (isAnonymousUrl(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

    /**
     * 检查是否是允许匿名访问的URL
     */
    private boolean isAnonymousUrl(String requestURI) {
        // 静态资源
        if (requestURI.endsWith(".html") || requestURI.endsWith(".css") || requestURI.endsWith(".js")) {
            return true;
        }
        // 登录相关
        if (requestURI.equals("/login") || requestURI.equals("/register") || requestURI.equals("/captchaImage")) {
            return true;
        }
        // Swagger相关
        if (requestURI.startsWith("/swagger-ui.html") || requestURI.startsWith("/swagger-resources") 
            || requestURI.startsWith("/webjars") || requestURI.startsWith("/v3/api-docs")) {
            return true;
        }
        // Druid相关
        if (requestURI.startsWith("/druid")) {
            return true;
        }
        return false;
    }
}
