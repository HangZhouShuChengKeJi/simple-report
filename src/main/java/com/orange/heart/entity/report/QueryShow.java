package com.orange.heart.entity.report;

import java.io.Serializable;

public class QueryShow implements Serializable {
    /**
     * 展示code
     */
    private String code;

    /**
     * 展示名称
     */
    private String name;

    /**
     * 展示值
     */
    private Object value;

    /**
     * 展示枚举值
     */
    private String showEnum;

    /**
     * 展示类型 0 字符串 1 整型 2 时间空间 3 图片
     */
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShowEnum() {
        return showEnum;
    }

    public void setShowEnum(String showEnum) {
        this.showEnum = showEnum;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}