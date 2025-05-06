package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户登录记录对象 app_login_records
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Data
public class AppLoginRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "登录时间", width = 60, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /** 登录ip */
    @Excel(name = "登录ip")
    private String ipAddress;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 设备信息 */
    @Excel(name = "设备信息")
    private String deviceInfo;

    /** 浏览器信息 */
    @Excel(name = "浏览器信息")
    private String browserInfo;

    /** 操作系统 */
    @Excel(name = "操作系统")
    private String osInfo;

    /** user_agent */
    @Excel(name = "user_agent")
    private String userAgent;

    /** 国家 */
    @Excel(name = "国家")
    private String locationCountry;

    /** 州/省 */
    @Excel(name = "州/省")
    private String locationRegion;

    /** 城市 */
    @Excel(name = "城市")
    private String locationCity;

    /** 登录方法 */
    @Excel(name = "登录方法")
    private String loginMethod;

    /** 登录结果 */
    @Excel(name = "登录结果")
    private Integer success;

    /** 失败原因 */
    @Excel(name = "失败原因")
    private String failureReason;

    /** 会话ID */
    @Excel(name = "会话ID")
    private String sessionId;

    /** 双因素认证 */
    @Excel(name = "双因素认证")
    private Integer twoFactorUsed;

    /** 登录持续时长 */
    @Excel(name = "登录持续时长")
    private Long loginDuration;

    /** 新设备 */
    @Excel(name = "新设备")
    private Integer isNewDevice;

    /** 新地理位置 */
    @Excel(name = "新地理位置")
    private Integer isNewLocation;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("loginTime", getLoginTime())
            .append("ipAddress", getIpAddress())
            .append("deviceType", getDeviceType())
            .append("deviceInfo", getDeviceInfo())
            .append("browserInfo", getBrowserInfo())
            .append("osInfo", getOsInfo())
            .append("userAgent", getUserAgent())
            .append("locationCountry", getLocationCountry())
            .append("locationRegion", getLocationRegion())
            .append("locationCity", getLocationCity())
            .append("loginMethod", getLoginMethod())
            .append("success", getSuccess())
            .append("failureReason", getFailureReason())
            .append("sessionId", getSessionId())
            .append("twoFactorUsed", getTwoFactorUsed())
            .append("loginDuration", getLoginDuration())
            .append("isNewDevice", getIsNewDevice())
            .append("isNewLocation", getIsNewLocation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
