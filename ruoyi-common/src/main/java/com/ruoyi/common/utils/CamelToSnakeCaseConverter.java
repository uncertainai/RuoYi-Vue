package com.ruoyi.common.utils;


public class CamelToSnakeCaseConverter {
    public static String camelToSnake(String camelCaseStr) {
        StringBuilder result = new StringBuilder();

        // 将第一个字符转换为小写
        result.append(Character.toLowerCase(camelCaseStr.charAt(0)));

        // 遍历字符串的其余部分
        for (int i = 1; i < camelCaseStr.length(); i++) {
            char ch = camelCaseStr.charAt(i);
            // 如果是大写字符，前面加下划线，并转换为小写
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String camelCaseStr = "SummaryColumn";
        String snakeCaseStr = camelToSnake(camelCaseStr);
        System.out.println(snakeCaseStr); // 输出: summary_column
    }
}
