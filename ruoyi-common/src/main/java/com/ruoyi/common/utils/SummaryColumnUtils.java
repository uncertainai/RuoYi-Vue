package com.ruoyi.common.utils;


import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.PageField;
import com.ruoyi.common.core.domain.SummaryColumn;
import com.ruoyi.common.enums.SummaryElTypeEnum;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SummaryColumnUtils {

    public static List<SummaryColumn> get(Class<?> clazz) {
        List<SummaryColumn> columns = new ArrayList<>();
        Field[] allFields = FieldUtils.getAllFields(clazz);
        for (Field field: allFields) {
            Excel annotation = field.getAnnotation(Excel.class);
            if (null == annotation) {
                continue;
            }
            SummaryColumn column = SummaryColumn.builder()
                    .name(annotation.name())
                    .field(field.getName())
                    .column(CamelToSnakeCaseConverter.camelToSnake(field.getName()))
                    .canSummary(FieldChecker.isNumericField(field))
                    .canGroupBy(!FieldChecker.isNumericField(field))
                    .canQuery(!FieldChecker.isNumericField(field))
                    .canOrderBy(FieldChecker.isNumericField(field))
                    .elType(getElType(field))
                    .cond(FieldChecker.isDateField(field) ? "between" : "=")
                    .build();
            PageField summary = field.getAnnotation(PageField.class);
            if (null != summary) {
                column.setCanSummary(summary.canSummary() != -1 ? summary.canSummary() == 1 : column.isCanSummary());
                column.setCanGroupBy(summary.canGroupBy() != -1 ? summary.canGroupBy() == 1 : column.isCanGroupBy());
                column.setCanQuery(summary.canQuery() != -1 ? summary.canQuery() == 1 : column.isCanQuery());
                column.setCanOrderBy(summary.canOrderBy() != -1 ? summary.canOrderBy() == 1 : column.isCanOrderBy());
                column.setElType(summary.elType().getValue());
                column.setCond(summary.elType() == SummaryElTypeEnum.DATE ? "between" : column.getCond());
            }
            columns.add(column);
        }
        return columns;
    }

    public static String getElType(Field field) {
        return FieldChecker.isDateField(field) ? "date" : FieldChecker.isBool(field) ? "bool" : null;
    }
}
