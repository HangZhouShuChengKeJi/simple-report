package com.orange.heart.entity;

import java.io.Serializable;
import java.util.Date;

public class RReportSql implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 任务code
     */
    private String sqlCode;

    /**
     * 名称
     */
    private String name;

    /**
     * sql
     */
    private String sql;

    /**
     * 数据源
     */
    private String dataSource;

    /**
     * 展示列名
     */
    private String showColumn;

    /**
     * 
     */
    private String param;

    /**
     * 字典值
     */
    private Integer pageSize;

    /**
     * 1：查询结果list 2：查询结果number
     */
    private Integer resultType;

    /**
     * 1：正常 2：删除
     */
    private Integer state;

    /**
     * 通知邮箱
     */
    private String noticeEmail;

    /**
     * 调度计划
     */
    private String schedulePlan;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * r_report_sql
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 任务code
     * @return sql_code 任务code
     */
    public String getSqlCode() {
        return sqlCode;
    }

    /**
     * 任务code
     * @param sqlCode 任务code
     */
    public void setSqlCode(String sqlCode) {
        this.sqlCode = sqlCode;
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sql
     * @return sql sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * sql
     * @param sql sql
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * 数据源
     * @return data_source 数据源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据源
     * @param dataSource 数据源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 展示列名
     * @return show_column 展示列名
     */
    public String getShowColumn() {
        return showColumn;
    }

    /**
     * 展示列名
     * @param showColumn 展示列名
     */
    public void setShowColumn(String showColumn) {
        this.showColumn = showColumn;
    }

    /**
     * 
     * @return param 
     */
    public String getParam() {
        return param;
    }

    /**
     * 
     * @param param 
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 字典值
     * @return page_size 字典值
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 字典值
     * @param pageSize 字典值
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 1：查询结果list 2：查询结果number
     * @return result_type 1：查询结果list 2：查询结果number
     */
    public Integer getResultType() {
        return resultType;
    }

    /**
     * 1：查询结果list 2：查询结果number
     * @param resultType 1：查询结果list 2：查询结果number
     */
    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    /**
     * 1：正常 2：删除
     * @return state 1：正常 2：删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 1：正常 2：删除
     * @param state 1：正常 2：删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 通知邮箱
     * @return notice_email 通知邮箱
     */
    public String getNoticeEmail() {
        return noticeEmail;
    }

    /**
     * 通知邮箱
     * @param noticeEmail 通知邮箱
     */
    public void setNoticeEmail(String noticeEmail) {
        this.noticeEmail = noticeEmail;
    }

    /**
     * 调度计划
     * @return schedule_plan 调度计划
     */
    public String getSchedulePlan() {
        return schedulePlan;
    }

    /**
     * 调度计划
     * @param schedulePlan 调度计划
     */
    public void setSchedulePlan(String schedulePlan) {
        this.schedulePlan = schedulePlan;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}