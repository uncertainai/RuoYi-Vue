package com.ruoyi.common.enums;


public enum SummaryElTypeEnum {
    DATE("date", "日期"),
    INPUT("input", "输入"),
    SELECT("select", "选择框"),
    ;

    private final String value;
    private final String desc;

    SummaryElTypeEnum(String value, String desc)
    {
        this.value = value;
        this.desc = desc;
    }

    public String getValue()
    {
        return value;
    }
    public String getDesc()
    {
        return desc;
    }
}