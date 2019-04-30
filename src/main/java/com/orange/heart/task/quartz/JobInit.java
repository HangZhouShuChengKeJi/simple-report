package com.orange.heart.task.quartz;

import com.orange.heart.service.QuartzService;
import com.orange.heart.service.RReportSqlService;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("jobInit")
public class JobInit implements InitializingBean {

	@Resource
	private RReportSqlService rReportSqlService;

	@Resource
	private QuartzService quartzService;

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init() throws SchedulerException {

		// 这里获取任务信息数据
		List<ScheduleJob> jobList = rReportSqlService.getDBJob();
		if (CollectionUtils.isEmpty(jobList)) {
			return;
		}

		for (ScheduleJob job : jobList) {
			quartzService.startJob(job, rReportSqlService);
		}
	}

}
