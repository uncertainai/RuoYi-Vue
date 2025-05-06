package com.ruoyi.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 用户认证服务
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 从数据库加载用户信息
        // 这里暂时使用硬编码的用户信息进行测试
        if ("admin".equals(username)) {
            return new User(username, 
                "$2a$10$X/hX4Jz9z9z9z9z9z9z9zO9z9z9z9z9z9z9z9z9z9z9z9z9z9z9", // 密码：admin123
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
} 