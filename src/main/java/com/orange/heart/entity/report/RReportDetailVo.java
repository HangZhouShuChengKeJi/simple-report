package com.orange.heart.entity.report;

import com.orange.heart.entity.RReport;

import java.util.List;

/**
 * 该软件代码知识产权隶属于杭州数橙网络科技有限公司，属公司机密。请勿外传或拷贝
 * 类说明：报表具体详情
 *
 * @Author: Administrator
 * @Date: 2019/1/31 15:26
 * @Version 1.0
 */
public class RReportDetailVo  extends RReport {

    /**
     * 报表的查询条件
     */
   private List<QueryParam> queryParams;

    /**
     *报表展示内容
     */
    private List<QueryShow> queryShows;


    /**
     * 报表sql
     */
    private String reportSql;


    public List<QueryParam> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(List<QueryParam> queryParams) {
        this.queryParams = queryParams;
    }

    public List<QueryShow> getQueryShows() {
        return queryShows;
    }

    public void setQueryShows(List<QueryShow> queryShows) {
        this.queryShows = queryShows;
    }

    public String getReportSql() {
        return reportSql;
    }

    public void setReportSql(String reportSql) {
        this.reportSql = reportSql;
    }
}
