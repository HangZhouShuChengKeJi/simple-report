package com.orange.heart.task.quartz;

import com.orange.heart.service.RReportSqlService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定时任务运行工厂类
 * 
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

	private static final Log NOTICE_LOGGER = LogFactory.getLog("task");


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		if (scheduleJob == null) {
			return;
		}
		RReportSqlService rReportSqlService = (RReportSqlService) context.getMergedJobDataMap().get("a");
		if (rReportSqlService == null) {
			return;
		}
		
		NOTICE_LOGGER.debug("任务成功运行, " + "任务名称 = [" + scheduleJob.getJobName() + "]");
		rReportSqlService.doExecute(scheduleJob.getrReportSql());
		NOTICE_LOGGER.debug("任务完成, " + "任务名称 = [" + scheduleJob.getJobName() + "]");
	}
}