package com.orange.heart.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @table r_report
 * @author MyBatis Generator
 * @version 1.0.0, 2019-02-21 15:44:51
 */
public class RReport implements Serializable {
    /**
     * 
     * 
     * @column r_report.id
     */
    private Integer id;

    /**
     * 关键字
     * 
     * @column r_report.keyword
     */
    private String keyword;

    /**
     * url访问关键字
     * 
     * @column r_report.visit_key
     */
    private String visitKey;

    /**
     * 报表名称
     * 
     * @column r_report.name
     */
    private String name;

    /**
     * 状态 1有效 0 无效
     * 
     * @column r_report.state
     */
    private Integer state;

    /**
     * 1:mysql 2:hive
     * 
     * @column r_report.from
     */
    private Integer from;

    /**
     * 导出功能：1开通 0关闭
     * 
     * @column r_report.export_flag
     */
    private Integer exportFlag;

    /**
     * 创建时间
     * 
     * @column r_report.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * 
     * @column r_report.update_time
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @column r_report.id
     * 
     * @return 
     */
    public Integer getId() {
        return id;
    }

    /**
     * @column r_report.id
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @column r_report.keyword
     * 
     * @return 关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @column r_report.keyword
     * 
     * @param keyword 关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @column r_report.visit_key
     * 
     * @return url访问关键字
     */
    public String getVisitKey() {
        return visitKey;
    }

    /**
     * @column r_report.visit_key
     * 
     * @param visitKey url访问关键字
     */
    public void setVisitKey(String visitKey) {
        this.visitKey = visitKey;
    }

    /**
     * @column r_report.name
     * 
     * @return 报表名称
     */
    public String getName() {
        return name;
    }

    /**
     * @column r_report.name
     * 
     * @param name 报表名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @column r_report.state
     * 
     * @return 状态 1有效 0 无效
     */
    public Integer getState() {
        return state;
    }

    /**
     * @column r_report.state
     * 
     * @param state 状态 1有效 0 无效
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @column r_report.from
     * 
     * @return 1:mysql 2:hive
     */
    public Integer getFrom() {
        return from;
    }

    /**
     * @column r_report.from
     * 
     * @param from 1:mysql 2:hive
     */
    public void setFrom(Integer from) {
        this.from = from;
    }

    /**
     * @column r_report.export_flag
     * 
     * @return 导出功能：1开通 0关闭
     */
    public Integer getExportFlag() {
        return exportFlag;
    }

    /**
     * @column r_report.export_flag
     * 
     * @param exportFlag 导出功能：1开通 0关闭
     */
    public void setExportFlag(Integer exportFlag) {
        this.exportFlag = exportFlag;
    }

    /**
     * @column r_report.create_time
     * 
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @column r_report.create_time
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @column r_report.update_time
     * 
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @column r_report.update_time
     * 
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}