package com.ruoyi.common.config;


import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PathVariableListResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PathVariableList.class);
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // 检查请求方法是否为 DELETE，如果不是则返回 null 跳过处理
        if (!"DELETE".equalsIgnoreCase(request.getMethod())) {
            return null;
        }

        PathVariableList annotation = parameter.getParameterAnnotation(PathVariableList.class);
        String delimiter = annotation.delimiter();

        // 获取路径变量的名称
        String variableName = parameter.getParameterName();

        // 获取路径变量
        Map<String, String> uriTemplateVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String pathVariable = uriTemplateVariables.get(variableName);

        if (pathVariable == null) {
            throw new IllegalArgumentException("Path variable not found: " + variableName);
        }

        // 根据参数类型进行转换
        Class<?> parameterType = parameter.getParameterType();
        if (parameterType == List.class) {
            Class<?> genericType = (Class<?>) ((java.lang.reflect.ParameterizedType) parameter.getGenericParameterType()).getActualTypeArguments()[0];
            if (genericType == Integer.class) {
                return Arrays.stream(pathVariable.split(delimiter))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } else if (genericType == Long.class) {
                return Arrays.stream(pathVariable.split(delimiter))
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
            } else if (genericType == String.class) {
                return Arrays.stream(pathVariable.split(delimiter))
                        .collect(Collectors.toList());
            } else {
                throw new IllegalArgumentException("Unsupported parameter type: " + genericType);
            }
        } else {
            throw new IllegalArgumentException("Unsupported parameter type: " + parameterType);
        }
    }
}