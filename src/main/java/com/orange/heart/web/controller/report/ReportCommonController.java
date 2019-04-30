/**
 *
 */
package com.orange.heart.web.controller.report;

import com.orange.commons.utils.PageInfo;
import com.orange.commons.utils.RequestUtil;
import com.orange.heart.cache.RCrudColumnCache;
import com.orange.heart.cache.RdictInfoCache;
import com.orange.heart.cache.ReportInfoCache;
import com.orange.heart.common.CTBException;
import com.orange.heart.constant.HeartConstant;
import com.orange.heart.dao.JdbcTemplateDao;
import com.orange.heart.entity.CrudQueryVo;
import com.orange.heart.entity.RCrudColumn;
import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.report.QueryParam;
import com.orange.heart.entity.report.QueryParamRequest;
import com.orange.heart.entity.report.QueryShow;
import com.orange.heart.entity.report.QueryShowVo;
import com.orange.heart.service.PoiService;
import com.orange.heart.service.RReportSqlService;
import com.orange.heart.service.ReportCommonService;
import com.orange.heart.service.ReportManageService;
import com.orange.heart.util.ChartUtil;
import com.orange.heart.util.SqlUtil;
import com.orange.heart.web.controller.BaseAdminController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表展示公共Controller
 */
@Controller
public class ReportCommonController extends BaseAdminController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ReportInfoCache reportInfoCache;

    @Resource
    private RCrudColumnCache rCrudColumnCache;

    @Resource
    private PoiService poiService;

    @Resource
    private RReportSqlService rReportSqlService;
    @Resource
    private RdictInfoCache    rdictInfoCache;


    /**
     * 展示报表
     *
     * @param visitKey
     * @param request
     * @param response
     *
     * @return
     */
    @RequestMapping("/report/{visitKey}.htm")
    public ModelAndView all(@PathVariable("visitKey") String visitKey, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(visitKey)) {
            ModelAndView mav = new ModelAndView("/admin/report/reportNotFound");
            return mav;
        }
        RReportSql rReportSql = reportInfoCache.getSql(visitKey);
        if (rReportSql == null) {
            ModelAndView mav = new ModelAndView("/admin/report/reportNotFound");
            return mav;
        }
        ModelAndView mav = new ModelAndView("/admin/report/reportPreview");
        // 查询条件
        List<QueryParam> paramList = SqlUtil.listQueryParam(rReportSql.getParam());
        mav.addObject("queryColumnList", paramList);
        QueryParamRequest query = initParam(request, paramList);
        int v = RequestUtil.getInt(request, "v");
        if (v != -1) {
            try {
                QueryShowVo vo = getQueryShowVo(visitKey, paramList, query);
                // mysq查询
                mav.addObject("dataList", vo.getDataList());
            } catch (CTBException e) {
                mav.addObject("errorMsg", e.getMessage());
                logger.error(MessageFormatter.arrayFormat("visitKey={}", new Object[]{visitKey}).getMessage(), e);
            }
        }
        mav.addObject("showColumnList", SqlUtil.listShowColumn(rReportSql.getShowColumn()));
        mav.addObject("showReportList", reportInfoCache.getAllCanShowReport());
        mav.addObject("visitKey", visitKey);
        mav.addObject("query", query);
        mav.addObject("paramValueMap", query.getParamValueMap());
        mav.addObject("reportName", reportInfoCache.getTableName(visitKey));
        mav.addObject("rReport", reportInfoCache.getRReport(visitKey));
        mav.addObject("dictTypeListMap", rdictInfoCache.getDictTypeListMap());
        mav.addObject("dictTypeMap", rdictInfoCache.getDictTypeMap());
        return mav;
    }

    private QueryShowVo getQueryShowVo(@PathVariable("visitKey") String visitKey, List<QueryParam> paramList, QueryParamRequest query) {
        // 校验参数
        Map<String, Object> paramMap = query.getParamValueMap();
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }
        for (QueryParam queryParam : paramList) {
            // 参数值
            Object o = paramMap.get(queryParam.getParamCode());
            if (StringUtils.equals(queryParam.getIsMust(), String.valueOf(HeartConstant.YES_NO.YES)) && o == null) {
                throw new CTBException("必填项【" + queryParam.getParamName() + "】为空");
            }
        }
        return rReportSqlService.doExecuteQuerySql(visitKey, query);
    }

    private QueryParamRequest initParam(HttpServletRequest request, List<QueryParam> paramList) {
        QueryParamRequest query = new QueryParamRequest();

        int pageNo = RequestUtil.getInt(request, "pageNo");
        if (pageNo == -1) {
            pageNo = 1;
        }
        query.setPageNo(pageNo);
        query.setQueryParams(paramList);
        if (CollectionUtils.isEmpty(paramList)) {
            return query;
        }
        Map<String, Object> paramValueMap = new HashMap<String, Object>();
        // 组装参数
        for (QueryParam queryParam : paramList) {
            // 区间参数
            if (StringUtils.equals(HeartConstant.PARAM_TYPE.DATE_TIME_RANGE, queryParam.getType()) || StringUtils.equals(HeartConstant.PARAM_TYPE.NUMBER_RANGE, queryParam.getType())) {
                String tempStartKey = queryParam.getParamCode() + "_start";
                String tempStartValue = RequestUtil.getString(request, tempStartKey);
                if (StringUtils.isNotBlank(tempStartValue)) {
                    paramValueMap.put(tempStartKey, tempStartValue);
                }
                String tempEndKey = queryParam.getParamCode() + "_end";
                String tempEndValue = RequestUtil.getString(request, tempEndKey);
                if (StringUtils.isNotBlank(tempEndValue)) {
                    paramValueMap.put(tempEndKey, tempEndValue);
                }
            } else {
                String tempValue = RequestUtil.getString(request, queryParam.getParamCode());
                if (StringUtils.isNotBlank(tempValue)) {
                    // 默认
                    paramValueMap.put(queryParam.getParamCode(), tempValue);
                }
            }
        }
        query.setParamValueMap(paramValueMap);
        return query;
    }


    private List<CrudQueryVo> dealWithParam(HttpServletRequest request, String tableKeyword) {
        // 组装获取查询参数
        List<RCrudColumn> queryCrudColumnList = rCrudColumnCache.getQueryListByKeyword(tableKeyword);
        Map<String, String> queryMap = new HashMap<String, String>();
        List<CrudQueryVo> paramList = new ArrayList<CrudQueryVo>();
        if (CollectionUtils.isNotEmpty(queryCrudColumnList)) {
            for (RCrudColumn crudColumn : queryCrudColumnList) {

                CrudQueryVo crudQueryVo = new CrudQueryVo();
                crudQueryVo.setColumnId(crudColumn.getColumnId());
                crudQueryVo.setColumnName(crudColumn.getColumnName());
                crudQueryVo.setColumnType(crudColumn.getColumnType());

                if (crudColumn.getColumnType() != null && crudColumn.getColumnType() == 2) {
                    String[] strarr = request.getParameterValues(crudColumn.getColumnId());

                    if (strarr != null && strarr.length > 0) {
                        List<String> list = new ArrayList<String>();
                        for (int i = 0; i < strarr.length; i++) {
                            list.add(strarr[i]);
                        }
                        crudQueryVo.setInputValueList(list);
                    }
                } else {
                    String objStr = RequestUtil.getString(request, crudColumn.getColumnId(), "");
                    // mysql查询参数
                    queryMap.put(crudColumn.getColumnId(), objStr);
                    // hive查询的参数
                    crudQueryVo.setInputValue(objStr);
                }

                paramList.add(crudQueryVo);
            }
        }
        return paramList;
    }


    /**
     * 导出
     *
     * @param request
     * @param response
     * @param visitKey
     */
    @RequestMapping("/report/{visitKey}/exportData.htm")
    public void downloadQuerySmsOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable String visitKey) {
        int pageNo = 1;
        String pageSizeStr = RequestUtil.getString(request, "maxExportCount");
        int pageSize = 20000;
        if (StringUtils.isNotBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        RReportSql rReportSql = reportInfoCache.getSql(visitKey);
        if (rReportSql == null) {
            poiService.writeToClient(response, null);
            return;
        }
        List<QueryParam> paramList = SqlUtil.listQueryParam(rReportSql.getParam());
        QueryParamRequest query = initParam(request, paramList);
        query.setPageNo(pageNo);
        query.setPageSize(pageSize);
        QueryShowVo vo = getQueryShowVo(visitKey, paramList, query);

        if (vo == null || CollectionUtils.isEmpty(vo.getDataList())) {
            poiService.writeToClient(response, null);
            return;
        }
        List<QueryShow> queryShows = SqlUtil.listShowColumn(rReportSql.getShowColumn());
        if (CollectionUtils.isEmpty(queryShows)) {
            poiService.writeToClient(response, null);
            return;
        }
        List<String> columnList = new ArrayList<>();
        List<String> columnNameList = new ArrayList<>();
        for (QueryShow queryShow : queryShows) {
            columnList.add(queryShow.getCode());
            columnNameList.add(queryShow.getName());
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (List<QueryShow> data : vo.getDataList()) {
            Map<String, Object> map = new HashMap<>();
            for (QueryShow bean : data) {
                map.put(bean.getCode(), bean.getValue());
            }
            list.add(map);
        }
        HSSFWorkbook wb = poiService.getQueryResultExcel(list, columnList, columnNameList);
        poiService.writeToClient(response, wb);
    }

}
