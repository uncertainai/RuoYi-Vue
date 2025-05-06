package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.SummaryElTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 页面字段配置
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PageField {
    /**
     * -1 = 未设置
     * 1 = true
     * 0 = false
     * @return
     */
    int canQuery() default -1;
    int canSummary() default -1;
    int canGroupBy() default -1;
    int canOrderBy() default -1;

    SummaryElTypeEnum elType() default SummaryElTypeEnum.INPUT;
}
