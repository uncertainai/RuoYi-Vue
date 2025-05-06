package com.ruoyi.framework.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.crypto.SecretKey;

/**
 * JWT配置
 * 
 * @author ruoyi
 */
@Configuration
public class JwtConfig {
    
    @Bean
    public SecretKey key() {
        // 使用 Keys.secretKeyFor 生成安全的密钥
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
} 