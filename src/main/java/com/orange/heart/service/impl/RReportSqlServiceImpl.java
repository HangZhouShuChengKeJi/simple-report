package com.orange.heart.service.impl;

import com.orange.commons.utils.ResourceUtil;
import com.orange.heart.cache.ReportInfoCache;
import com.orange.heart.dao.JdbcTemplateDao;
import com.orange.heart.dao.RReportSqlDAO;
import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.RReportSqlExample;
import com.orange.heart.entity.report.QueryParamRequest;
import com.orange.heart.entity.report.QueryShowVo;
import com.orange.heart.mail.TestMailSender;
import com.orange.heart.service.PoiService;
import com.orange.heart.service.RReportSqlService;
import com.orange.heart.task.quartz.ScheduleJob;
import com.orange.heart.util.SystemEmailSupport;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RReportSqlServiceImpl implements RReportSqlService {

	protected static final Logger	logger	= Logger.getLogger("task");

	@Resource
	private RReportSqlDAO rReportSqlDAO;

	@Resource
	private JdbcTemplateDao jdbcTemplateDao;
	
	@Resource
	private PoiService		poiService;

	@Resource
	private ReportInfoCache reportInfoCache;


	/**
	 * 获取所有正常状态的任务
	 */
	@Override
	public List<ScheduleJob> getDBJob() {

		RReportSqlExample example = new RReportSqlExample();
		example.createCriteria().andStateEqualTo(1);
		List<RReportSql> list = rReportSqlDAO.selectByExample(example);

		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (RReportSql sql : list) {
				jobList.add(convertJobBean(sql));
			}
		}
		return jobList;
	}

	/**
	 * 组装任务
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public ScheduleJob convertJobBean(RReportSql sql) {
		ScheduleJob job = new ScheduleJob();
		job.setJobId("10000" + sql.getId());
		job.setJobName(sql.getSqlCode());
		job.setJobGroup("hiveWork");
		// job.setJobStatus("1");
		job.setCronExpression(sql.getSchedulePlan());
		// job.setDesc("数据导入任务");
		job.setrReportSql(sql);
		return job;
	}

	@Override
	public void doExecute(RReportSql sql) {
		if (sql == null) {
			return;
		}
		if (StringUtils.isBlank(sql.getDataSource())) {
			return;
		}

		// 只有一个数据库 就不判断是那个数据源了
		// 判断sql类型
		String sqlStr = sql.getSql();
		if (StringUtils.isBlank(sqlStr)) {
			return;
		}
		String[] arr = StringUtils.split(sqlStr.toString(), " ");
		if (StringUtils.equals(arr[0].toLowerCase(), "insert")) {
			jdbcTemplateDao.insertSql(sqlStr, null);
		} else {
			List<Map<String, Object>> list = jdbcTemplateDao.queryByPagingBySql(sql.getSql(), null);
			sendEmail(list, sql);
		}

	}

	private void sendEmail(List<Map<String, Object>> list, RReportSql sql) {
		if (CollectionUtils.isEmpty(list) || sql == null || StringUtils.isBlank(sql.getNoticeEmail())
				|| StringUtils.isBlank(sql.getShowColumn())) {
			logger.debug("发送邮件失败:参数设置不正确");
			return;
		}

		StringBuffer th = new StringBuffer();
		List<String> columnList = new ArrayList<String>();
		List<String> decodeList = new ArrayList<String>();
		String[] columnArr = StringUtils.split(sql.getShowColumn(), ",");
		List<String> columnNameList = new ArrayList<String>();
		if (columnArr != null) {
			for (int i = 0; i < columnArr.length; i++) {
				String[] arr = StringUtils.split(columnArr[i], "|");
				if (arr == null || arr.length != 3) {
					continue;
				}
				th.append("<th align='left'>").append(arr[1]).append("</th>");
				columnList.add(arr[0]);
				decodeList.add(arr[2]);
				columnNameList.add(arr[1]);
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>");
		sb.append("<html lang=\"zh-CN\">");
		sb.append("<body>");
		sb.append("<table class=\"table table-bordered table-hover table-striped\" border=\"1\">");
		sb.append("<tr>");
		sb.append(th);
		sb.append("</tr>");

		for (Map<String, Object> beanMap : list) {
			sb.append("<tr>");
			for (int i = 0; i < columnList.size(); i++) {
				String tempValue = String.valueOf(beanMap.get(columnList.get(i)));
				if (tempValue == null || StringUtils.equals(tempValue, "null")) {
					tempValue = "";
				}
				if (StringUtils.equals(decodeList.get(i), "1")) {
					// tempValue = WarehouseUtil.decryptFromBase64(tempValue);
					sb.append("<td align='center'>").append(tempValue).append("</td>");
					// 保存解密之后， 后面就不用再解密了
					beanMap.put(columnList.get(i), tempValue);
				} else {
					sb.append("<td align='center'>").append(tempValue).append("</td>");
				}
			}
			sb.append("</tr>");
		}
		sb.append("</table \">");
		sb.append(" </body>").append("</html>");

		String[] sendToEmail = StringUtils.split(sql.getNoticeEmail(), ",");
		String[] files = null;
		
//		 // 将生成excel文件保存到指定路径下  
//		String filePath = ResourceUtil.getSystem("attach.file.path");
//		if (StringUtils.isNotBlank(filePath)) {
//			makeSureFileExists(filePath);
//			String file = filePath + sql.getName()+ "_"+ DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+ ".xls";
//			FileOutputStream fout = null;
//			try {
//				HSSFWorkbook wb = poiService.getQueryResultExcel(list, columnList, columnNameList);
//				fout = new FileOutputStream(file);
//				wb.write(fout);
//				files = new String[] { file };
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				if (fout != null) {
//					try {
//						fout.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
		
		// logger.debug(sb.toString());
		// 发送邮件
		for (int i = 1; i <= 5; i++) {
			boolean rs = TestMailSender.sendMail(SystemEmailSupport.getEmail(),
					ResourceUtil.getSystem("emailPassword"), sendToEmail, sql.getName(), sb.toString(), files);
			logger.debug("第" + i + "次邮件发送结果：" + rs + " 发送邮箱：");
			if (rs) {
				break;
			}
		}
	}

	/**
	 * 翻页数据查询
	 * @param sqlKey
	 * @param query
	 * @return
	 */
	@Override
	public QueryShowVo doExecuteQuerySql(String sqlKey, QueryParamRequest query ) {
		QueryShowVo vo = new QueryShowVo();
		RReportSql sql = reportInfoCache.getSql(sqlKey);
		if (sql == null) {
			return vo;
		}
		if (StringUtils.isBlank(sql.getDataSource())) {
			return vo;
		}
		// 只有一个数据库 就不判断是那个数据源了
		// 判断sql类型
		return jdbcTemplateDao.queryByPaging(sql, query);
	}
	
	private void makeSureFileExists(String filePath) {
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

}
