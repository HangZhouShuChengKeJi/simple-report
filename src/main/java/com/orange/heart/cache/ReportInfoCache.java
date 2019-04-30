package com.orange.heart.cache;

import com.orange.heart.dao.RReportDAO;
import com.orange.heart.dao.RReportSqlDAO;
import com.orange.heart.entity.RReport;
import com.orange.heart.entity.RReportExample;
import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.RReportSqlExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author allen
 */
@Service("reportInfoCache")
public class ReportInfoCache implements InitializingBean {

    @Resource
    private RReportDAO    rReportDAO;
    @Resource
    private RReportSqlDAO rReportSqlDAO;


    private static Map<String, RReport> visitTabMap;

    private static List<RReport> showReportList;

    private static Map<String, RReportSql> sqlMap = new HashMap<String, RReportSql>();

    @Override
    public void afterPropertiesSet() throws Exception {
        refresh();
    }

    public void refresh() {
        List<RReport> crudVisitTabList = rReportDAO.selectByExample(new RReportExample());
        showReportList = new ArrayList<RReport>();
        visitTabMap = new HashMap<String, RReport>();

        List<String> keyList = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(crudVisitTabList)) {
            for (RReport rReport : crudVisitTabList) {
                if (rReport.getState() != null && rReport.getState() == 1) {
                    visitTabMap.put(rReport.getVisitKey(), rReport);
                    showReportList.add(rReport);
                    keyList.add(String.valueOf(rReport.getId()));
                }
            }
        }
        // sql
        if (CollectionUtils.isNotEmpty(keyList)) {
            RReportSqlExample sqlExample = new RReportSqlExample();
            sqlExample.createCriteria().andSqlCodeIn(keyList);
            List<RReportSql> sqlList = rReportSqlDAO.selectByExample(sqlExample);
            if (CollectionUtils.isNotEmpty(sqlList)) {
                for (RReportSql sql : sqlList) {
                    sqlMap.put(sql.getSqlCode(), sql);
                }
            }
        }

    }

    /**
     * @param visitKey
     *
     * @return
     */
    public RReport getRReport(String visitKey) {
        return visitTabMap.get(visitKey);
    }

    /**
     * @param key
     *
     * @return
     */
    public RReportSql getSql(String key) {
        RReport rReport = visitTabMap.get(key);
        if (rReport == null){
            return null;
        }
        RReportSql rReportSql = sqlMap.get(String.valueOf(rReport.getId()));
        if (rReportSql == null) {
            return null;
        }
        return rReportSql;
    }

    /**
     * @param crudVisitKey
     *
     * @return
     */
    public String getTableName(String crudVisitKey) {
        RReport visit = visitTabMap.get(crudVisitKey);
        if (visit == null) {
            return "";
        }
        return visit.getName();
    }

    /**
     * @return
     */
    public List<RReport> getAllCanShowReport() {
        return showReportList;
    }

}
