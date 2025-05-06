package com.ruoyi.common.core.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class SummaryColumn {
    /**
     * Java字段
     */
    private String field;
    /**
     * 数据库字段
     */
    private String column;
    /**
     * 字段名称
     */
    private String name;
    /**
     * 字段输入类型
     */
    private String elType;
    /**
     * 字段是否可以进行统计
     */
    private boolean canSummary;

    /**
     * 是否允许分组
     */
    @Builder.Default
    private boolean canGroupBy = true;

    /**
     * 是否允许作为查询条件
     */
    @Builder.Default
    private boolean canQuery = true;
    /**
     * 是否参与排序
     */
    private boolean canOrderBy;

    /**
     * 查询条件
     */
    @Builder.Default
    private String cond = "=";
    /**
     * 字段值
     */
    private Object value;
    /**
     * 倒序
     */
    @Builder.Default
    private boolean desc = true;
}