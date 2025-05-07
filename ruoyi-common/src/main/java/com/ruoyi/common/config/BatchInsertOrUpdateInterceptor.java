package com.ruoyi.common.config;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
public class BatchInsertOrUpdateInterceptor implements InnerInterceptor {
    private static final Set<String> MYSQL_KEYWORDS = new HashSet<>();


    static {
        // 添加MySQL关键词
        String[] keywords = {
            "SHOW"
        };

        for (String keyword : keywords) {
            MYSQL_KEYWORDS.add(keyword);
        }
    }

    public static boolean isMysqlKeyword(String word) {
        return MYSQL_KEYWORDS.contains(word.toUpperCase());
    }

    /**
     * 批量操作拦截器
     * 缺点：没有预编译sql条件 直接拼值。可能会造成sql注入
     */
    @Override
    public void beforePrepare(StatementHandler statementHandler, Connection connection, Integer transactionTimeout) {
        BoundSql boundSql = statementHandler.getBoundSql();
        Object parameterObject = boundSql.getParameterObject();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String methodName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1);


        // 只拦截指定的方法名
        if ("batchInsertOrUpdate".equals(methodName)) {
            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
            List<?> dataList = (List<?>) paramMap.get("list");

            if (!dataList.isEmpty()) {
                Object firstEntity = dataList.get(0);
                String tableName = getTableName(firstEntity.getClass());
                List<Field> fields = getFields(firstEntity.getClass());
                Map<String, String> columnFieldMap = fields.stream()
                        .collect(Collectors.toMap(BatchInsertOrUpdateInterceptor::getColumnName, Field::getName));

                columnFieldMap.remove(null);
                columnFieldMap.remove("id");


                List<String> sqlList = new ArrayList<>();

                List<Object> valueList = new ArrayList<>();

                for (int i = 0; i < dataList.size(); i++) {
                    Object entity = dataList.get(i);
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO ").append(tableName).append(" (");


                    StringBuilder columnSql = new StringBuilder();
                    Map<String, Object> rowMap = new HashMap<>();
                    for (String column : columnFieldMap.keySet()) {
                        Object value = getValue(columnFieldMap, entity, column);
                        if (null == value) {
                            continue;
                        }
                        if (isMysqlKeyword(column)) {
                            column = "`" + column + "`";
                        }
                        columnSql.append(column).append(",");
                        rowMap.put(column, value);
                        valueList.add(value);
                    }

                    if (StringUtils.isEmpty(columnSql)) {
                        continue;
                    }

                    sql.append(columnSql.substring(0, columnSql.length() - 1));

                    sql.append(") VALUES (").append(
                            rowMap.keySet().stream().map(v -> "?").collect(Collectors.joining(","))
                    );

                    sql.append(") ON DUPLICATE KEY UPDATE ");
                    boolean first = true;
                    for (String column : rowMap.keySet()) {
                        if (!first) {
                            sql.append(", ");
                        }
                        if (isMysqlKeyword(column)) {
                            column = "`" + column + "`";
                        }
                        sql.append(column).append(" = VALUES(").append(column).append(")");
                        first = false;
                    }
                    sqlList.add(sql.toString());
                }


                // 将多个 SQL 语句组合成一个执行块
                String finalSql = String.join("; ", sqlList);
//                log.info("batchInsertOrUpdate sql is {}", finalSql);
                String mid = UUID.randomUUID().toString().replaceAll("-", "");
                // 获取 BoundSql 对象
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(finalSql);

                    for (int i = 0; i < valueList.size(); i++) {
                        preparedStatement.setObject(i+1, valueList.get(i));
                    }
                    preparedStatement.addBatch();

                    // 执行批量插入
                    preparedStatement.executeBatch();
                } catch (Exception exception) {
                    log.error("{}, 批量操作异常", mid, exception);
                }
            }
        }

    }



    private Object getValue(Map<String, String> columnFieldMap, Object entity, String column) {
        Field field;
        try {
            field = entity.getClass().getDeclaredField(columnFieldMap.get(column));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(entity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return value;
    }


    private static String getTableName(Class<?> clazz) {
        String className = clazz.getSimpleName();
        TableName annotation = clazz.getAnnotation(TableName.class);
        if (null != annotation) {
            return annotation.value();
        }

        StringBuilder tableName = new StringBuilder();
        for (int i = 0; i < className.length(); i++) {
            char c = className.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                tableName.append('_');
            }
            tableName.append(Character.toLowerCase(c));
        }
        return tableName.toString();
    }

    private static List<Field> getFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Field[] allFields = FieldUtils.getAllFields(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                continue; // 跳过静态字段
            }
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField == null || tableField.exist()) {
                fields.add(field);
            }
        }
        return fields;
    }

    private static String getColumnName(Field field) {
        TableField tableField = field.getAnnotation(TableField.class);
        if (tableField != null && !tableField.value().isEmpty()) {
            if (!tableField.exist()) {
                return null;
            }
            return tableField.value();
        }
        return convertToSnakeCase(field.getName());
    }

    private static String convertToSnakeCase(String camelCase) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                result.append('_');
            }
            result.append(Character.toLowerCase(c));
        }
        return result.toString();
    }


}