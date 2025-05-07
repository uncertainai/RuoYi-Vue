package com.ruoyi.api.service;

import com.ruoyi.system.domain.AppUsers;
import com.ruoyi.system.domain.AppLoginRecords;

public interface SocialAuthService {
    
    /**
     * Handle Google OAuth2 login
     * @param code Authorization code from Google
     * @return AppUsers object with user information
     */
    AppUsers handleGoogleLogin(String code);
    
    /**
     * Handle TikTok OAuth2 login
     * @param code Authorization code from TikTok
     * @return AppUsers object with user information
     */
    AppUsers handleTikTokLogin(String code);
    
    /**
     * Handle Facebook OAuth2 login
     * @param code Authorization code from Facebook
     * @return AppUsers object with user information
     */
    AppUsers handleFacebookLogin(String code);
    
    /**
     * Create or update user record
     * @param user User information from social media
     * @param provider Provider name (GOOGLE, TIKTOK, FACEBOOK)
     * @return Updated AppUsers object
     */
    AppUsers createOrUpdateUser(AppUsers user, String provider);
    
    /**
     * Create login record
     * @param user User information
     * @param ipAddress IP address
     * @param userAgent User agent string
     * @return Created login record
     */
    AppLoginRecords createLoginRecord(AppUsers user, String ipAddress, String userAgent);

    /**
     * Get user session from Redis
     * @param sessionKey Session key
     * @return User information if session exists, null otherwise
     */
    AppUsers getSession(String sessionKey);
} 