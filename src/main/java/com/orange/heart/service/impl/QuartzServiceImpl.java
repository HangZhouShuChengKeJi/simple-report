package com.orange.heart.service.impl;

import com.orange.heart.constant.HeartConstant.YES_NO;
import com.orange.heart.dao.RReportSqlDAO;
import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.RReportSqlExample;
import com.orange.heart.service.QuartzService;
import com.orange.heart.service.RReportSqlService;
import com.orange.heart.task.quartz.QuartzJobFactory;
import com.orange.heart.task.quartz.ScheduleJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuartzServiceImpl implements QuartzService {

	@Resource
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Resource
	private RReportSqlDAO rReportSqlDAO;
	

	/**
	 * 暂停任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public void resumeJob(String jobName, String jobGroup) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public void deleteJob(String jobName, String jobGroup) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		scheduler.deleteJob(jobKey);
		
		RReportSql updateRecord = new RReportSql();
		updateRecord.setState(YES_NO.NO);
		RReportSqlExample example = new RReportSqlExample();
		example.createCriteria().andSqlCodeEqualTo(jobName);
		rReportSqlDAO.updateByExampleSelective(updateRecord, example);
	}

	/**
	 * 更新任务的时间表达式
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @param cronExpression
	 * @throws SchedulerException
	 */
	public void rescheduleJobNewCronExpression(String jobName, String jobGroup, String cronExpression)
			throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		// 按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 启动
	 * @param job
	 * @param rReportSqlService
	 * @throws SchedulerException
	 */
	public void startJob(ScheduleJob job, RReportSqlService rReportSqlService) throws SchedulerException {
		if (job == null || rReportSqlService == null){
			return ;
		}
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
					.withIdentity(job.getJobName(), job.getJobGroup()).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			jobDetail.getJobDataMap().put("a", rReportSqlService);

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
					.withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

}
