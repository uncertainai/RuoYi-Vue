package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 剧目类型内容对象 app_genre_contents
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Data
public class AppGenreContents extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private Long genreId;

    /** 语言 */
    @Excel(name = "语言")
    private String languageCode;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String genreName;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("genreId", getGenreId())
            .append("languageCode", getLanguageCode())
            .append("genreName", getGenreName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
