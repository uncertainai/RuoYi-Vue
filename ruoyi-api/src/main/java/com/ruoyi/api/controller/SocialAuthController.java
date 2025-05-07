package com.ruoyi.api.controller;

import com.ruoyi.api.service.SocialAuthService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.AppLoginRecords;
import com.ruoyi.system.domain.AppUsers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/social")
public class SocialAuthController {

    @Autowired
    private SocialAuthService socialAuthService;

    @GetMapping("/google")
    public AjaxResult googleLogin(@RequestParam String code, HttpServletRequest request) {
        try {
            AppUsers user = socialAuthService.handleGoogleLogin(code);
            AppLoginRecords loginRecord = socialAuthService.createLoginRecord(
                user,
                request.getRemoteAddr(),
                request.getHeader("User-Agent")
            );
            
            return AjaxResult.success("Login successful", loginRecord);
        } catch (Exception e) {
            return AjaxResult.error("Google login failed: " + e.getMessage());
        }
    }

    @GetMapping("/tiktok")
    public AjaxResult tiktokLogin(@RequestParam String code, HttpServletRequest request) {
        try {
            AppUsers user = socialAuthService.handleTikTokLogin(code);
            AppLoginRecords loginRecord = socialAuthService.createLoginRecord(
                user,
                request.getRemoteAddr(),
                request.getHeader("User-Agent")
            );
            
            return AjaxResult.success("Login successful", loginRecord);
        } catch (Exception e) {
            return AjaxResult.error("TikTok login failed: " + e.getMessage());
        }
    }

    @GetMapping("/facebook")
    public AjaxResult facebookLogin(@RequestParam String code, HttpServletRequest request) {
        try {
            AppUsers user = socialAuthService.handleFacebookLogin(code);
            AppLoginRecords loginRecord = socialAuthService.createLoginRecord(
                user,
                request.getRemoteAddr(),
                request.getHeader("User-Agent")
            );
            
            return AjaxResult.success("Login successful", loginRecord);
        } catch (Exception e) {
            return AjaxResult.error("Facebook login failed: " + e.getMessage());
        }
    }

    @GetMapping("/session/{sessionId}")
    public AjaxResult getSession(@PathVariable String sessionId) {
        try {
            String sessionKey = "user_session:" + sessionId;
            AppUsers user = socialAuthService.getSession(sessionKey);
            if (user != null) {
                return AjaxResult.success(user);
            }
            return AjaxResult.error("Session not found");
        } catch (Exception e) {
            return AjaxResult.error("Failed to get session: " + e.getMessage());
        }
    }
} 