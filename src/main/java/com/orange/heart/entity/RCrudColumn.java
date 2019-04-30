package com.orange.heart.entity;

import java.io.Serializable;
import java.util.Date;

public class RCrudColumn implements Serializable {
    private Integer id;

    private String keyword;

    private String columnName;

    private Integer columnType;

    private String columnId;

    private String dictInfo;

    private Integer required;

    private String dataFormat;

    private String regularExpress;

    private Integer maxlength;

    private String styleExpress;

    private Integer sort;

    private Integer addFlag;

    private Integer editFlag;

    private Integer listFlag;

    private Integer queryFlag;

    private String queryFormat;

    private String hrefColumn;

    private Integer detailFlag;

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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getColumnType() {
        return columnType;
    }

    public void setColumnType(Integer columnType) {
        this.columnType = columnType;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getDictInfo() {
        return dictInfo;
    }

    public void setDictInfo(String dictInfo) {
        this.dictInfo = dictInfo;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getRegularExpress() {
        return regularExpress;
    }

    public void setRegularExpress(String regularExpress) {
        this.regularExpress = regularExpress;
    }

    public Integer getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(Integer maxlength) {
        this.maxlength = maxlength;
    }

    public String getStyleExpress() {
        return styleExpress;
    }

    public void setStyleExpress(String styleExpress) {
        this.styleExpress = styleExpress;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getAddFlag() {
        return addFlag;
    }

    public void setAddFlag(Integer addFlag) {
        this.addFlag = addFlag;
    }

    public Integer getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(Integer editFlag) {
        this.editFlag = editFlag;
    }

    public Integer getListFlag() {
        return listFlag;
    }

    public void setListFlag(Integer listFlag) {
        this.listFlag = listFlag;
    }

    public Integer getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(Integer queryFlag) {
        this.queryFlag = queryFlag;
    }

    public String getQueryFormat() {
        return queryFormat;
    }

    public void setQueryFormat(String queryFormat) {
        this.queryFormat = queryFormat;
    }

    public String getHrefColumn() {
        return hrefColumn;
    }

    public void setHrefColumn(String hrefColumn) {
        this.hrefColumn = hrefColumn;
    }

    public Integer getDetailFlag() {
        return detailFlag;
    }

    public void setDetailFlag(Integer detailFlag) {
        this.detailFlag = detailFlag;
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