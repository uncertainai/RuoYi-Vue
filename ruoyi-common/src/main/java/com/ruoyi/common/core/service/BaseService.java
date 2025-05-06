package com.ruoyi.common.core.service;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.*;
import java.util.*;

@Slf4j
public class BaseService<T> implements IBaseService<T> {

    private Class<T> type;

    private String importDataMethodName = "batchInsertOrUpdate";
    private Method importDataMethod;
    private Class<?> importDataClass;

    public BaseService() {
        init();
    }

    /**
     * 下载导入模板
     */
    @Override
    public void downloadImportModule() {
        HttpServletResponse response = getHttpServletResponse();
        new ExcelUtil<>(type).exportExcel(response, Collections.emptyList(), "导入模板");

    }
    /**
     * 导入数据
     */
    @Override
    public void importData() {
        HttpServletRequest request = getCurrentHttpRequest();
        if (request != null && request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                this.importData(entry.getValue());
            }
        }
    }

    /**
     * 导入数据
     */
    @Override
    public void importData(MultipartFile file) {
        importData(new ExcelUtil<>(type).convFile(file));
    }


    public void importData(List<T> list) {
        try {
            this.importDataMethod.invoke(SpringUtils.getBean(importDataClass), list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private void init() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            // 获取泛型类型参数
            this.type = (Class<T>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        } else {
            throw new RuntimeException("Unable to determine generic type.");
        }


        try {
            importDataMethod = this.getClass().getMethod(importDataMethodName, List.class);
            importDataClass = this.getClass();
        } catch (NoSuchMethodException e) {
            Field[] fields = FieldUtils.getAllFields(this.getClass());
            for (Field field : fields) {
                if (setImportMethod(field.getType())) {
                    break;
                }
            }
        }
        if (null == importDataMethod) {
            log.info("没有找到批量新增方法: {}", this.getClass().getName());
        }
    }

    public static Class<?> getGenericType(Class<?> clazz) {
        if (clazz.getGenericInterfaces().length < 1) {
            return null;
        }
        Type genericInterface = clazz.getGenericInterfaces()[0];
        if (genericInterface instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                return (Class<?>) actualTypeArguments[0];
            }
        }
        return null;
    }



    public boolean setImportMethod(Class<?> clazz) {
        try {
            Class<?> genericType = getGenericType(clazz);
            if (null != genericType && genericType == this.type) {
                importDataMethod = clazz.getMethod(importDataMethodName, List.class);
                importDataClass = clazz;
                log.info("{} 找到导入方法: 在{}", this.getClass().getName(), clazz.getName());
                return true;
            } else if (genericType != null) {
                log.info("找到导入方法，但不是同一个数据对象. {}", clazz.getName());
            }
        } catch (NoSuchMethodException e) {
        }
        return false;
    }



    private HttpServletResponse getHttpServletResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletResponse response = requestAttributes.getResponse();
        return response;
    }

    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}
