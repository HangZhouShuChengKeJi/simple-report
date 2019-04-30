package com.orange.heart.entity.report;

import java.io.Serializable;

public class QueryParam implements Serializable {
    /**
     * 参数code
     */
    private String paramCode;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数类型 0 字符串 1 整型 2 时间空间 3 下拉框
     */
    private String type;

    /**
     * 参数枚举值
     */
    private String paramEnum;

    /**
     * 是否必须填写
     */
    private String isMust;


    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParamEnum() {
        return paramEnum;
    }

    public void setParamEnum(String paramEnum) {
        this.paramEnum = paramEnum;
    }

    public String getIsMust() {
        return isMust;
    }

    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }
}