package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 国家语言配置对象 app_countries_languages
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Data
public class AppCountriesLanguages extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 国家编码（ISO 3166-1 Alpha-2） */
    @Excel(name = "国家编码", readConverterExp = "I=SO,3=166-1,A=lpha-2")
    private String countryCode;

    /** 语言（ISO 639-1 或其他标准）  */
    @Excel(name = "语言", readConverterExp = "I=SO,6=39-1,或=其他标准")
    private String languageCode;

    /** 国家的默认语言 */
    @Excel(name = "国家的默认语言")
    private Integer isDefault;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("countryCode", getCountryCode())
            .append("languageCode", getLanguageCode())
            .append("isDefault", getIsDefault())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
