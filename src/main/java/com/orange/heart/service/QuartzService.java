package com.orange.heart.service;

import com.orange.heart.task.quartz.ScheduleJob;
import org.quartz.SchedulerException;

public interface QuartzService {

	/**
	 * 暂停任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	void pauseJob(String jobName, String jobGroup) throws SchedulerException;

	/**
	 * 恢复任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	void resumeJob(String jobName, String jobGroup) throws SchedulerException;

	/**
	 * 删除任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	void deleteJob(String jobName, String jobGroup) throws SchedulerException;

	/**
	 * 更新任务的时间表达式
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @param cronExpression
	 * @throws SchedulerException
	 */
	void rescheduleJobNewCronExpression(String jobName, String jobGroup, String cronExpression)
			throws SchedulerException;

	/**
	 * 启动
	 * 
	 * @param job
	 * @param rReportSqlService
	 * @throws SchedulerException
	 */
	void startJob(ScheduleJob job, RReportSqlService rReportSqlService) throws SchedulerException;

}
