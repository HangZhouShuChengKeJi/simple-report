package com.orange.heart.service;

import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.report.QueryParamRequest;
import com.orange.heart.entity.report.QueryShowVo;
import com.orange.heart.task.quartz.ScheduleJob;

import java.util.List;

public interface RReportSqlService {

	/**
	 * 获取所有需要启动的任务
	 * 
	 * @return
	 */
	List<ScheduleJob> getDBJob();

	/**
	 * 组装任务
	 * 
	 * @param sql
	 * @return
	 */
	public ScheduleJob convertJobBean(RReportSql sql);

	/**
	 * 执行
	 * 
	 * @param sql
	 */
	void doExecute(RReportSql sql);

	/**
	 * 翻页数据查询
	 * @param sqlKey
	 * @param query
	 * @return
	 */
	QueryShowVo doExecuteQuerySql(String sqlKey, QueryParamRequest query);

}
