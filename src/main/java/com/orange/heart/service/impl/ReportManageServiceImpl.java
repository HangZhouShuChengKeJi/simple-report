package com.orange.heart.service.impl;

import com.orange.heart.constant.HeartConstant;
import com.orange.heart.dao.RReportDAO;
import com.orange.heart.dao.RReportSqlDAO;
import com.orange.heart.entity.RReport;
import com.orange.heart.entity.RReportExample;
import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.RReportSqlExample;
import com.orange.heart.entity.report.RReportDetailVo;
import com.orange.heart.service.ReportManageService;
import com.orange.heart.util.SqlUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报表管理
 *
 * @author allen
 * @date 2019/01/31
 */
@Service
public class ReportManageServiceImpl implements ReportManageService {

    @Resource
    private RReportDAO rReportDAO;

    @Resource
    private RReportSqlDAO rReportSqlDAO;

    @Override
    public RReport selectRReportByKey(String tableKey) {
        if (StringUtils.isBlank(tableKey)) {
            return null;
        }
        RReportExample example = new RReportExample();
        example.createCriteria().andKeywordEqualTo(tableKey);
        List<RReport> rsList = rReportDAO.selectByExample(example);
        if (CollectionUtils.isNotEmpty(rsList)) {
            return rsList.get(0);
        }
        return null;
    }

    /**
     * 查询报表列表
     *
     * @param query
     *
     * @return
     */
    @Override
    public List<RReport> queryReportList(RReportExample query) {
        if (query == null) {
            return null;
        }
        query.setTotalCount((int)rReportDAO.countByExample(query));
        return rReportDAO.selectByExample(query);

    }


    /**
     * 查询报表详情
     *
     * @param reportId
     *
     * @return
     */
    @Override
    public RReportDetailVo queryReportList(Integer reportId) {
        if (reportId == null) {
            return null;
        }
        RReport rReport = rReportDAO.selectByPrimaryKey(reportId);
        if (rReport == null) {
            return null;
        }
        RReportDetailVo vo = new RReportDetailVo();
        BeanUtils.copyProperties(rReport, vo);
        // 查询sql信息
        RReportSqlExample sqlExample = new RReportSqlExample();
        sqlExample.createCriteria().andSqlCodeEqualTo(String.valueOf(rReport.getId()));
        List<RReportSql> sqlList = rReportSqlDAO.selectByExample(sqlExample);
        if (CollectionUtils.isNotEmpty(sqlList)) {
            RReportSql rReportSql = sqlList.get(0);
            vo.setReportSql(rReportSql.getSql());
            vo.setQueryParams(SqlUtil.listQueryParam(rReportSql.getParam()));
            vo.setQueryShows(SqlUtil.listShowColumn(rReportSql.getShowColumn()));
        }
        return vo;
    }


    @Override
    public int saveRReportDetailVo(RReportDetailVo vo) {
        // 新增
        if (vo.getId() == null) {
            RReport rReport = new RReport();
            rReport.setKeyword(vo.getKeyword());
            rReport.setName(vo.getName());
            rReport.setVisitKey(vo.getVisitKey());
            rReport.setState(HeartConstant.YES_NO.YES);
            rReport.setExportFlag(vo.getExportFlag());
            int rs = rReportDAO.insertSelective(rReport);
            if (rs > 0) {
                RReportSql rReportSql = new RReportSql();
                rReportSql.setSqlCode(String.valueOf(rReport.getId()));
                rReportSql.setSql(vo.getReportSql());
                rReportSql.setParam(SqlUtil.convertQueryParamListToString(vo.getQueryParams()));
                rReportSql.setShowColumn(SqlUtil.convertShowColumnListToString(vo.getQueryShows()));
                rReportSql.setDataSource("ctb");
                rReportSqlDAO.insertSelective(rReportSql);
            }
            return rs;
        } else {
            // 更新
            RReport updateRReport = new RReport();
            updateRReport.setId(vo.getId());
            updateRReport.setKeyword(vo.getKeyword());
            updateRReport.setName(vo.getName());
            updateRReport.setVisitKey(vo.getVisitKey());
            updateRReport.setState(HeartConstant.YES_NO.YES);
            updateRReport.setExportFlag(vo.getExportFlag());
            rReportDAO.updateByPrimaryKeySelective(updateRReport);

            RReportSql updateRReportSql = new RReportSql();
            updateRReportSql.setSqlCode(vo.getKeyword());
            updateRReportSql.setSql(vo.getReportSql());
            updateRReportSql.setParam(SqlUtil.convertQueryParamListToString(vo.getQueryParams()));
            updateRReportSql.setShowColumn(SqlUtil.convertShowColumnListToString(vo.getQueryShows()));

            RReportSqlExample updateExample = new RReportSqlExample();
            updateExample.createCriteria().andSqlCodeEqualTo(String.valueOf(vo.getId()));
            rReportSqlDAO.updateByExampleSelective(updateRReportSql, updateExample);
            return 1;
        }
    }

    @Override
    public int deleteRReport(Integer reportId) {
        if (reportId == null) {
            return 0;
        }
        int rs = rReportDAO.deleteByPrimaryKey(reportId);
        if (rs > 0) {
            RReportSqlExample deleteExample = new RReportSqlExample();
            deleteExample.createCriteria().andSqlCodeEqualTo(String.valueOf(reportId));
            rReportSqlDAO.deleteByExample(deleteExample);
        }
        return rs;
    }

}
