package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户观看记录对象 app_user_watch_records
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Data
public class AppUserWatchRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    /** 剧目 */
    @Excel(name = "剧目")
    private Long dramaId;

    /** 第几秒 */
    @Excel(name = "第几秒")
    private Long dramaSecond;

    /** 观看时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "观看时间", width = 60, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date watchedAt;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("dramaId", getDramaId())
            .append("dramaSecond", getDramaSecond())
            .append("watchedAt", getWatchedAt())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
