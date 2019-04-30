/**
 * 
 */
package com.orange.heart.service;

import com.orange.heart.entity.RReport;
import com.orange.heart.entity.RReportExample;
import com.orange.heart.entity.report.RReportDetailVo;

import java.util.List;

/**
 * 报表管理
 *
 * @author allen
 * @date 2019/01/31
 */
public interface ReportManageService {

	/**
	 * 查询报表详情
	 * @param tableKey
	 * @return
	 */
	RReport selectRReportByKey(String tableKey);


	/**
	 * 查询报表列表
	 * @param query
	 * @return
	 */
	List<RReport> queryReportList(RReportExample query);


	/**
	 * 查询报表详情
	 * @param reportId
	 * @return
	 */
	RReportDetailVo queryReportList(Integer reportId);

	/**
	 * 保存报表详情
	 * @param vo
	 * @return
	 */
	int saveRReportDetailVo(RReportDetailVo vo);

	/**
	 * 删除报表
	 * @param reportId
	 * @return
	 */
	int deleteRReport(Integer reportId);

}
