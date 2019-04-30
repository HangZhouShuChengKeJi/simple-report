/**
 *
 */
package com.orange.heart.web.controller.admin;

import com.orange.commons.utils.RequestUtil;
import com.orange.commons.web.json.JsonModel;
import com.orange.heart.cache.RCrudColumnCache;
import com.orange.heart.cache.RdictInfoCache;
import com.orange.heart.cache.ReportInfoCache;
import com.orange.heart.dao.RReportDAO;
import com.orange.heart.entity.RReport;
import com.orange.heart.entity.RReportExample;
import com.orange.heart.entity.report.QueryParam;
import com.orange.heart.entity.report.QueryShow;
import com.orange.heart.entity.report.RReportDetailVo;
import com.orange.heart.service.ReportManageService;
import com.orange.heart.util.JsonSupport;
import com.orange.heart.web.controller.BaseAdminController;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 报表管理
 */
@Controller
public class ReportManageController extends BaseAdminController {

    @Resource
    private RReportDAO rReportDAO;

    @Resource
    private ReportManageService reportManageService;

    @Resource
    private ReportInfoCache reportInfoCache;

    @Resource
    private RCrudColumnCache rCrudColumnCache;

    @Resource
    private RdictInfoCache rdictInfoCache;

    @RequestMapping("/admin/report/reportList.htm")
    public ModelAndView query(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        RReportExample query = new RReportExample();
        query.setPageNo(pageNo);
        query.setPageSize(10);
        query.setTotalCount((int)rReportDAO.countByExample(query));
        List<RReport> reportList = rReportDAO.selectByExample(query);

        ModelAndView mav = new ModelAndView("/admin/report/reportList");
        mav.addObject("dataList", reportList);
        mav.addObject("query", query);
        mav.addObject("allKeywordMap", rCrudColumnCache.getAllKeyword());
        return mav;
    }

    /**
     * 编辑初始化
     *
     * @return
     */
    @RequestMapping("/admin/report/editInit.htm")
    public ModelAndView editInit(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/admin/report/reportedit");
        int reportId = RequestUtil.getInt(request, "reportId");
        if (reportId != -1) {
            RReportDetailVo rReport = reportManageService.queryReportList(reportId);
            model.addObject("rReport", rReport);
        }
        int lastPageNo = RequestUtil.getInt(request, "lastPageNo");
        if (lastPageNo != -1) {
            model.addObject("lastPageNo", lastPageNo);
        }
        return model;
    }

    /**
     * 编辑提交
     *
     * @return
     */
    @RequestMapping("/admin/report/editSave.htm")
    public void editSave(HttpServletRequest request, HttpServletResponse response, RReportDetailVo rReport) {
        int reportId = RequestUtil.getInt(request, "reportId");

        JsonModel jsonModel = null;
        if (StringUtils.isBlank(rReport.getName()) || StringUtils.isBlank(rReport.getVisitKey()) || StringUtils.isBlank(rReport.getReportSql())) {
            jsonModel = JsonSupport.getJsonModel("", "2", "参数不完整！", 0);
            doWriter(request, response, jsonModel);
            return;
        }

        // 查询条件
        String[] paramCodeArr = request.getParameterValues("paramCode");
        String[] paramNameArr = request.getParameterValues("paramName");
        String[] paramTypeArr = request.getParameterValues("paramType");
        String[] paramEnumArr = request.getParameterValues("paramEnum");
        String[] paramIsMustArr = request.getParameterValues("paramIsMust");
        if (paramCodeArr != null) {
            if (paramCodeArr.length != paramNameArr.length || paramCodeArr.length != paramTypeArr.length || paramCodeArr.length != paramIsMustArr.length|| paramCodeArr.length != paramEnumArr.length) {
                jsonModel = JsonSupport.getJsonModel("", "2", "查询条件不完整！", 0);
                doWriter(request, response, jsonModel);
                return;
            }
            List<QueryParam> queryParams = new ArrayList<QueryParam>();
            for (int i = 0; i < paramCodeArr.length; i++) {
                if (StringUtils.isBlank(paramCodeArr[i])) {
                   jsonModel = JsonSupport.getJsonModel("", "2", "参数code不能为空！", 0);
                   doWriter(request, response, jsonModel);
                   return;
                }
                if (StringUtils.isBlank(paramNameArr[i])) {
                    jsonModel = JsonSupport.getJsonModel("", "2", "参数名称不能为空！", 0);
                    doWriter(request, response, jsonModel);
                    return;
                }
                if (StringUtils.isBlank(paramTypeArr[i])) {
                    jsonModel = JsonSupport.getJsonModel("", "2", "参数类型不能为空！", 0);
                    doWriter(request, response, jsonModel);
                    return;
                }
                if (StringUtils.isBlank(paramIsMustArr[i])) {
                    jsonModel = JsonSupport.getJsonModel("", "2", "是否必须填写不能为空！", 0);
                    doWriter(request, response, jsonModel);
                    return;
                }
                QueryParam queryParam = new QueryParam();
                queryParam.setParamCode(paramCodeArr[i]);
                queryParam.setParamName(paramNameArr[i]);
                queryParam.setType(paramTypeArr[i]);
                queryParam.setIsMust(paramIsMustArr[i]);
                queryParam.setParamEnum(paramEnumArr[i]);
                queryParams.add(queryParam);
            }
            rReport.setQueryParams(queryParams);
        }

        // 展示
        String[] showCodeArr = request.getParameterValues("showCode");
        String[] showNameArr = request.getParameterValues("showName");
        String[] showEnumArr = request.getParameterValues("showEnum");
        String[] showTypeArr = request.getParameterValues("showType");
        if (showCodeArr != null) {
            if (showCodeArr.length != showNameArr.length || showCodeArr.length != showTypeArr.length || showCodeArr.length != showEnumArr.length) {
                jsonModel = JsonSupport.getJsonModel("", "2", "展示列不完整！", 0);
                doWriter(request, response, jsonModel);
                return;
            }
            List<QueryShow> queryShows = new ArrayList<QueryShow>();
            for (int i = 0; i < showCodeArr.length; i++) {
                if (StringUtils.isBlank(showCodeArr[i])) {
                    jsonModel = JsonSupport.getJsonModel("", "2", "展示code不能为空！", 0);
                    doWriter(request, response, jsonModel);
                    return;
                }
                if (StringUtils.isBlank(showNameArr[i])) {
                    jsonModel = JsonSupport.getJsonModel("", "2", "展示名称不能为空！", 0);
                    doWriter(request, response, jsonModel);
                    return;
                }
                if (StringUtils.isBlank(showTypeArr[i])) {
                    jsonModel = JsonSupport.getJsonModel("", "2", "类型不能为空！", 0);
                    doWriter(request, response, jsonModel);
                    return;
                }
                QueryShow queryShow = new QueryShow();
                queryShow.setCode(showCodeArr[i]);
                queryShow.setName(showNameArr[i]);
                queryShow.setShowEnum(showEnumArr[i]);
                queryShow.setType(showTypeArr[i]);
                queryShows.add(queryShow);
            }
            rReport.setQueryShows(queryShows);
        }
        try{
            int rs = reportManageService.saveRReportDetailVo(rReport);
            if (rs != 1) {
                jsonModel = JsonSupport.getJsonModel("", "3", "保存失败！", 0);
            } else {
                jsonModel = JsonSupport.getJsonModel("", "1", "保存成功！", 0);
                reportInfoCache.refresh();
            }
        }catch (Exception e){
            jsonModel = JsonSupport.getJsonModel("", "3", "保存失败！" + e.getMessage(), 0);
        }

        doWriter(request, response, jsonModel);
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("/admin/report/delete.htm")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        int reportId = RequestUtil.getInt(request, "reportId");

        JsonModel jsonModel = null;
        if (reportId == -1) {
            jsonModel = JsonSupport.getJsonModel("", "2", "参数不完整！", 0);
        } else {
            int rs = reportManageService.deleteRReport(reportId);
            if (rs != 1) {
                jsonModel = JsonSupport.getJsonModel("", "3", "删除失败！", 0);
            } else {
                jsonModel = JsonSupport.getJsonModel("", "1", "删除成功！", 0);
            }
        }
        doWriter(request, response, jsonModel);
    }

    /**
     * 字典集刷新
     *
     * @return
     */
    @RequestMapping("/admin/rdictInfo/refresh.htm")
    public void refresh(HttpServletRequest request, HttpServletResponse response) {
        rdictInfoCache.refresh();
        JsonModel jsonModel = JsonSupport.getJsonModel("", "1", "刷新成功！", 0);
        doWriter(request, response, jsonModel);
    }

}
