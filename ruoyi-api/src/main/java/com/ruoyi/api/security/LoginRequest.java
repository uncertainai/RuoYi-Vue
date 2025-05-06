package com.ruoyi.api.security;

import lombok.Data;

/**
 * 登录请求对象
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
} 