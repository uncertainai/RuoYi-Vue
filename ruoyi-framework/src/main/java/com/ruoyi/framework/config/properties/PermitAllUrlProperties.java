package com.ruoyi.framework.config.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.ruoyi.common.annotation.Anonymous;

/**
 * 设置Anonymous注解允许匿名访问的url
 * 
 * @author ruoyi
 */
@Configuration
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware
{
    private ApplicationContext applicationContext;

    private List<String> urls = new ArrayList<>();

    @Override
    public void afterPropertiesSet()
    {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            // 获取方法上边的注解
            Anonymous method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Anonymous.class);
            Optional.ofNullable(method).ifPresent(anonymous -> Objects.requireNonNull(info.getPatternsCondition().getPatterns())
                    .forEach(url -> {
                        // 确保URL模式以/开头
                        if (!url.startsWith("/")) {
                            url = "/" + url;
                        }
                        // 处理路径变量和通配符
                        url = processUrlPattern(url);
                        urls.add(url);
                    }));

            // 获取类上边的注解
            Anonymous controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Anonymous.class);
            Optional.ofNullable(controller).ifPresent(anonymous -> Objects.requireNonNull(info.getPatternsCondition().getPatterns())
                    .forEach(url -> {
                        // 确保URL模式以/开头
                        if (!url.startsWith("/")) {
                            url = "/" + url;
                        }
                        // 处理路径变量和通配符
                        url = processUrlPattern(url);
                        urls.add(url);
                    }));
        });
    }

    /**
     * 处理URL模式中的路径变量和通配符
     */
    private String processUrlPattern(String url) {
        // 将 /** 替换为 /*
        url = url.replace("/**", "/*");
        // 将 {*xxx} 替换为 *
        url = url.replaceAll("\\{\\*\\w+\\}", "*");
        // 将 {xxx} 替换为 *
        url = url.replaceAll("\\{\\w+\\}", "*");
        return url;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        this.applicationContext = context;
    }

    public List<String> getUrls()
    {
        return urls;
    }

    public void setUrls(List<String> urls)
    {
        this.urls = urls;
    }
}
