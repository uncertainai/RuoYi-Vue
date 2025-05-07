package com.ruoyi.api.service.impl;

import com.ruoyi.api.service.SocialAuthService;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.AppLoginRecords;
import com.ruoyi.system.domain.AppUsers;
import com.ruoyi.system.service.IAppLoginRecordsService;
import com.ruoyi.system.service.IAppUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SocialAuthServiceImpl implements SocialAuthService {

    @Autowired
    private IAppUsersService appUsersService;

    @Autowired
    private IAppLoginRecordsService appLoginRecordsService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${oauth2.google.client-id}")
    private String googleClientId;

    @Value("${oauth2.google.client-secret}")
    private String googleClientSecret;

    @Value("${oauth2.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${oauth2.tiktok.client-id}")
    private String tiktokClientId;

    @Value("${oauth2.tiktok.client-secret}")
    private String tiktokClientSecret;

    @Value("${oauth2.tiktok.redirect-uri}")
    private String tiktokRedirectUri;

    @Value("${oauth2.facebook.client-id}")
    private String facebookClientId;

    @Value("${oauth2.facebook.client-secret}")
    private String facebookClientSecret;

    @Value("${oauth2.facebook.redirect-uri}")
    private String facebookRedirectUri;

    @Override
    public AppUsers handleGoogleLogin(String code) {
        // Exchange code for access token
        String accessToken = getGoogleAccessToken(code);
        // Get user info from Google
        AppUsers user = getGoogleUserInfo(accessToken);
        return createOrUpdateUser(user, "GOOGLE");
    }

    @Override
    public AppUsers handleTikTokLogin(String code) {
        // Exchange code for access token
        String accessToken = getTikTokAccessToken(code);
        // Get user info from TikTok
        AppUsers user = getTikTokUserInfo(accessToken);
        return createOrUpdateUser(user, "TIKTOK");
    }

    @Override
    public AppUsers handleFacebookLogin(String code) {
        // Exchange code for access token
        String accessToken = getFacebookAccessToken(code);
        // Get user info from Facebook
        AppUsers user = getFacebookUserInfo(accessToken);
        return createOrUpdateUser(user, "FACEBOOK");
    }

    @Override
    public AppUsers createOrUpdateUser(AppUsers user, String provider) {
        // Check if user exists
        AppUsers existingUser = appUsersService.selectList(new AppUsers() {{
            setProvider(provider);
            setProviderId(user.getProviderId());
        }}).stream().findFirst().orElse(null);

        if (existingUser != null) {
            // Update existing user
            user.setId(existingUser.getId());
            appUsersService.update(user);
            return user;
        } else {
            // Create new user
            appUsersService.insert(user);
            return user;
        }
    }

    @Override
    public AppLoginRecords createLoginRecord(AppUsers user, String ipAddress, String userAgent) {
        AppLoginRecords record = new AppLoginRecords();
        record.setUserId(user.getId());
        record.setLoginTime(DateUtils.getNowDate());
        record.setIpAddress(ipAddress);
        record.setUserAgent(userAgent);
        record.setLoginMethod(user.getProvider());
        record.setSuccess(1);
        record.setSessionId(UUID.randomUUID().toString());
        
        // Save to database
        appLoginRecordsService.insert(record);
        
        // Store session in Redis
        String sessionKey = "user_session:" + record.getSessionId();
        redisCache.setCacheObject(sessionKey, user, 24, TimeUnit.HOURS);
        
        return record;
    }

    @Override
    public AppUsers getSession(String sessionKey) {
        return redisCache.getCacheObject(sessionKey);
    }

    private String getGoogleAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", googleClientId);
        map.add("client_secret", googleClientSecret);
        map.add("code", code);
        map.add("redirect_uri", googleRedirectUri);
        map.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        // Make request to Google OAuth2 token endpoint
        // Return access token
        
        return "access_token"; // Placeholder
    }

    private AppUsers getGoogleUserInfo(String accessToken) {
        // Make request to Google user info endpoint
        // Parse response and create AppUsers object
        return new AppUsers(); // Placeholder
    }

    private String getTikTokAccessToken(String code) {
        // Similar to Google implementation
        return "access_token"; // Placeholder
    }

    private AppUsers getTikTokUserInfo(String accessToken) {
        // Similar to Google implementation
        return new AppUsers(); // Placeholder
    }

    private String getFacebookAccessToken(String code) {
        // Similar to Google implementation
        return "access_token"; // Placeholder
    }

    private AppUsers getFacebookUserInfo(String accessToken) {
        // Similar to Google implementation
        return new AppUsers(); // Placeholder
    }
} 