package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 剧目显示规则对象 app_drama_display_rules
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Data
public class AppDramaDisplayRules extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 剧目 */
    @Excel(name = "剧目")
    private Long dramaId;

    /** 国家编码 */
    @Excel(name = "国家编码")
    private String countryCode;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dramaId", getDramaId())
            .append("countryCode", getCountryCode())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
