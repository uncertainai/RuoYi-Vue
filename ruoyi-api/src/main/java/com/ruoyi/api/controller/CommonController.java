package com.ruoyi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private MessageSource messageSource;

    /**
     * 获取国际化消息
     */
    @GetMapping("/i18n")
    public Map<String, Object> getI18nMessage() {
        Map<String, Object> data = new HashMap<>();
        data.put("success", messageSource.getMessage("common.success", null, LocaleContextHolder.getLocale()));
        data.put("fail", messageSource.getMessage("common.fail", null, LocaleContextHolder.getLocale()));
        data.put("error", messageSource.getMessage("common.error", null, LocaleContextHolder.getLocale()));
        return data;
    }

    /**
     * 获取当前语言
     */
    @GetMapping("/locale")
    public Map<String, Object> getCurrentLocale() {
        Map<String, Object> data = new HashMap<>();
        data.put("locale", LocaleContextHolder.getLocale().toString());
        return data;
    }

    /**
     * 切换语言
     */
    @GetMapping("/switch")
    public Map<String, Object> switchLocale(@RequestParam String locale) {
        Map<String, Object> data = new HashMap<>();
        try {
            String[] language = locale.split("_");
            Locale newLocale = new Locale(language[0], language[1]);
            LocaleContextHolder.setLocale(newLocale);
            data.put("success", true);
            data.put("message", "Language switched successfully");
            data.put("locale", newLocale.toString());
        } catch (Exception e) {
            data.put("success", false);
            data.put("message", "Invalid locale format. Use format: language_country (e.g., zh_CN, en_US)");
        }
        return data;
    }
} 