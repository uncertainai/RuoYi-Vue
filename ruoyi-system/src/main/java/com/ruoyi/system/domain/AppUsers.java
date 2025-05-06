package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 社交媒体用户对象 app_users
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Data
public class AppUsers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String username;

    /** email邮箱 */
    @Excel(name = "email邮箱")
    private String email;

    /** 头像url */
    @Excel(name = "头像url")
    private String profilePicture;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 提供商 */
    @Excel(name = "提供商")
    private String provider;

    /** 提供商id */
    @Excel(name = "提供商id")
    private String providerId;

    /** 国家 */
    @Excel(name = "国家")
    private String countryCode;

    /** 语种 */
    @Excel(name = "语种")
    private String languageCode;

    /** access_token */
    @Excel(name = "access_token")
    private String accessToken;

    /** refresh_token */
    @Excel(name = "refresh_token")
    private String refreshToken;

    /** expires_at */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "expires_at", width = 60, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date expiresAt;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("email", getEmail())
            .append("profilePicture", getProfilePicture())
            .append("phoneNumber", getPhoneNumber())
            .append("provider", getProvider())
            .append("providerId", getProviderId())
            .append("countryCode", getCountryCode())
            .append("languageCode", getLanguageCode())
            .append("accessToken", getAccessToken())
            .append("refreshToken", getRefreshToken())
            .append("expiresAt", getExpiresAt())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
