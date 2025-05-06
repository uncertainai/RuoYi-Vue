package com.ruoyi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * API启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiApiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  API模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
} 