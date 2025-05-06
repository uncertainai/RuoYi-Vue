package com.ruoyi.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.html.EscapeUtil;

/**
 * XSS过滤处理
 * 
 * @author ruoyi
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    /**
     * @param request
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name)
    {
        String[] values = super.getParameterValues(name);
        if (values != null)
        {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++)
            {
                // 防xss攻击和过滤前后空格
                escapeValues[i] = EscapeUtil.clean(values[i]).trim();
            }
            return escapeValues;
        }
        return super.getParameterValues(name);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        // 非json类型，直接返回
        if (!isJsonRequest())
        {
            return super.getInputStream();
        }

        // 为空，直接返回
        String json = getBodyString(super.getInputStream());
        if (StringUtils.isEmpty(json))
        {
            return super.getInputStream();
        }

        // xss过滤
        json = EscapeUtil.clean(json).trim();
        final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream()
        {
            @Override
            public boolean isFinished()
            {
                return true;
            }

            @Override
            public boolean isReady()
            {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener)
            {
            }

            @Override
            public int read() throws IOException
            {
                return bis.read();
            }
        };
    }

    /**
     * 判断是否是json请求
     */
    public boolean isJsonRequest()
    {
        String header = super.getHeader("content-type");
        return StringUtils.containsAnyIgnoreCase(header, "application/json", "text/json");
    }

    /**
     * 获取请求体内容
     */
    private String getBodyString(ServletInputStream inputStream) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
        }
        return sb.toString();
    }
}