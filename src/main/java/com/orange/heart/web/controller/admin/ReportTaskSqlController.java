/**
 * 
 */
package com.orange.heart.web.controller.admin;

import com.orange.commons.utils.RequestUtil;
import com.orange.commons.web.json.JsonModel;
import com.orange.heart.constant.HeartConstant.QUARTZ;
import com.orange.heart.constant.HeartConstant.YES_NO;
import com.orange.heart.dao.RReportSqlDAO;
import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.RReportSqlExample;
import com.orange.heart.service.QuartzService;
import com.orange.heart.service.RReportSqlService;
import com.orange.heart.task.quartz.ScheduleJob;
import com.orange.heart.util.JsonSupport;
import com.orange.heart.web.controller.BaseAdminController;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通道情况
 * 
 */
@Controller
public class ReportTaskSqlController extends BaseAdminController {

	@Resource
	private SchedulerFactoryBean schedulerFactoryBean;

	@Resource
	private RReportSqlDAO rReportSqlDAO;

	@Resource
	private RReportSqlService rReportSqlService;

	@Resource
	private QuartzService quartzService;

	@RequestMapping("/admin/quartz/sqllist.htm")
	public ModelAndView sqllist(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {
		RReportSqlExample query = new RReportSqlExample();
		int pageNo = RequestUtil.getInt(request, "pageNo", 1);
		query.setPageNo(pageNo);
		query.setTotalCount(rReportSqlDAO.countByExample(query));
		List<RReportSql> reportList = rReportSqlDAO.selectByExample(query);
		ModelAndView mav = new ModelAndView("/admin/report/reportSqlList");
		mav.addObject("dataList", reportList);
		mav.addObject("query", query);
		if (CollectionUtils.isNotEmpty(reportList)) {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			Map<Integer, String> stateMap = new HashMap<Integer, String>();
			for (RReportSql sql : reportList) {
				TriggerKey triggerKey = TriggerKey.triggerKey(sql.getSqlCode(), QUARTZ.JOB_GROUP);
				Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
				stateMap.put(sql.getId(), triggerState.name());
			}
			mav.addObject("stateMap", stateMap);
		}
		return mav;
	}

	/**
	 * 任务暂停
	 * 
	 * @return
	 */
	@RequestMapping("/admin/quartz/operateJob.htm")
	public void operateJob(HttpServletRequest request, HttpServletResponse response) {

		String jobName = RequestUtil.getString(request, "jobName");
		int operateType = RequestUtil.getInt(request, "operateType", -1);
		JsonModel jsonModel = null;
		try {
			if (operateType == 1) {
				quartzService.pauseJob(jobName, QUARTZ.JOB_GROUP);
				jsonModel = JsonSupport.getJsonModel("", "1", "成功！", 0);
			} else if (operateType == 2) {
				quartzService.resumeJob(jobName, QUARTZ.JOB_GROUP);
				jsonModel = JsonSupport.getJsonModel("", "1", "成功！", 0);
			} else if (operateType == 3) {
				quartzService.deleteJob(jobName, QUARTZ.JOB_GROUP);
				jsonModel = JsonSupport.getJsonModel("", "1", "成功！", 0);
			} else {
				jsonModel = JsonSupport.getJsonModel("", "2", "失败！", 0);
			}

		} catch (SchedulerException e) {
			e.printStackTrace();
			jsonModel = JsonSupport.getJsonModel("", "2", "失败！", 0);
		}

		doWriter(request, response, jsonModel);
	}

	/**
	 * 启动任务
	 * 
	 * @return
	 */
	@RequestMapping("/admin/quartz/startJob.htm")
	public void startJob(HttpServletRequest request, HttpServletResponse response) {

		int jobId = RequestUtil.getInt(request, "jobId", -1);
		JsonModel jsonModel = null;
		try {
			if (jobId == -1) {
				jsonModel = JsonSupport.getJsonModel("", "2", "任务ID为空！", 0);
				doWriter(request, response, jsonModel);
				return;
			}
			RReportSql rReportSql = rReportSqlDAO.selectByPrimaryKey(jobId);
			if (rReportSql == null) {
				jsonModel = JsonSupport.getJsonModel("", "2", "任务不存在", 0);
				doWriter(request, response, jsonModel);
				return;
			}
			ScheduleJob job = rReportSqlService.convertJobBean(rReportSql);
			quartzService.startJob(job, rReportSqlService);
			RReportSql updateRecord = new RReportSql();
			updateRecord.setId(rReportSql.getId());
			updateRecord.setState(YES_NO.YES);
			rReportSqlDAO.updateByPrimaryKeySelective(updateRecord);

		} catch (SchedulerException e) {
			e.printStackTrace();
			jsonModel = JsonSupport.getJsonModel("", "2", "启动失败！", 0);
			doWriter(request, response, jsonModel);
			return;
		}
		jsonModel = JsonSupport.getJsonModel("", "1", "启动成功！", 0);
		doWriter(request, response, jsonModel);
	}

	@RequestMapping("/api/job/list.htm")
	public ModelAndView queryInfo(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {

		ModelAndView mav = new ModelAndView("/admin/report/channelreport");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScheduleJob job = new ScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDesc("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}

		return mav;
	}

	@RequestMapping("/api/job/aaa.htm")
	public ModelAndView aaa(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {

		ModelAndView mav = new ModelAndView("/admin/report/channelreport");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDesc("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return mav;
	}

}
