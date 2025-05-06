package com.ruoyi.common.utils;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class FieldChecker {
    public static boolean isNumericField(Field field) {
        Class<?> fieldType = field.getType();

        return fieldType == byte.class || fieldType == short.class || fieldType == int.class ||
                fieldType == long.class || fieldType == float.class || fieldType == double.class ||
                fieldType == Byte.class || fieldType == Short.class || fieldType == Integer.class ||
                fieldType == Long.class || fieldType == Float.class || fieldType == Double.class ||
                fieldType == BigDecimal.class || fieldType == BigInteger.class;
    }

    public static boolean isDateField(Field field) {
        Class<?> fieldType = field.getType();
        return Date.class.isAssignableFrom(fieldType) ||
                java.sql.Date.class.isAssignableFrom(fieldType) ||
                java.sql.Time.class.isAssignableFrom(fieldType) ||
                java.sql.Timestamp.class.isAssignableFrom(fieldType) ||
                LocalDate.class.isAssignableFrom(fieldType) ||
                LocalTime.class.isAssignableFrom(fieldType) ||
                LocalDateTime.class.isAssignableFrom(fieldType) ||
                ZonedDateTime.class.isAssignableFrom(fieldType);
    }

    public static boolean isBool(Field field) {
        Class<?> fieldType = field.getType();
        return Boolean.class.isAssignableFrom(fieldType);
    }
}
