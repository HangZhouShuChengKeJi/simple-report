package com.orange.heart.entity;

import java.io.Serializable;
import java.util.Date;

public class RReportChart implements Serializable {
    private Integer id;

    private String keyword;

    private String name;

    private String chartX;

    private String chartY;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChartX() {
        return chartX;
    }

    public void setChartX(String chartX) {
        this.chartX = chartX;
    }

    public String getChartY() {
        return chartY;
    }

    public void setChartY(String chartY) {
        this.chartY = chartY;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}