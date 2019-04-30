package com.orange.heart.web.controller.admin;

import com.orange.commons.utils.RequestUtil;
import com.orange.commons.web.json.JsonModel;
import com.orange.heart.cache.RCrudColumnCache;
import com.orange.heart.entity.Columns;
import com.orange.heart.entity.ColumnsExample;
import com.orange.heart.entity.RCrudColumn;
import com.orange.heart.service.RCrudColumnService;
import com.orange.heart.service.ReportManageService;
import com.orange.heart.util.JsonSupport;
import com.orange.heart.web.controller.BaseAdminController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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

/**
 * 列名信息修改
 * 
 * @author Administrator
 * 
 */
@Controller
public class CrudConfigController extends BaseAdminController {

	@Resource
	private RCrudColumnService rCrudColumnService;

	@Resource
	private RCrudColumnCache rCrudColumnCache;

	@Resource
	private ReportManageService reportManageService;

	@RequestMapping("/admin/report/crudconfig.htm")
	public ModelAndView query(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/screen/heart/admin/crudconfig");

		return mav;
	}

	@RequestMapping("/admin/report/showTalbeColumn.htm")
	public ModelAndView showHiveTalbe(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/screen/heart/admin/crudconfigedit");
		String tableName = RequestUtil.getString(request, "tableName", "");
		String refresh = RequestUtil.getString(request, "refresh", "");
		Map<String, String> allKeywordMap = rCrudColumnCache.getAllKeyword();
		Map<String, String> map = null;
		if (allKeywordMap.containsKey(tableName)) {
			// 查询数据库
			List<RCrudColumn> list = rCrudColumnCache.getAllListByKeyword(tableName);
			map = new HashMap<String, String>();
			Map<String, RCrudColumn> crudColumnMap = new HashMap<String, RCrudColumn>();
			// 刷新表结构
			Map<String, String> tempMap = null;
			if (StringUtils.equals("1", refresh)) {
				tempMap = getColumnsFromDB(tableName);
			}
			for (RCrudColumn rCrudColumn : list) {
				if (StringUtils.equals("1", refresh) && !tempMap.containsKey(rCrudColumn.getColumnId())) {
					// 刷新情况下,需要去除表机构中删除的字段
					continue;
				}
				map.put(rCrudColumn.getColumnId(), rCrudColumn.getColumnName());
				crudColumnMap.put(rCrudColumn.getColumnId(), rCrudColumn);
			}
			// 刷新情况下 增加新增的字段
			if (StringUtils.equals("1", refresh)) {

				for (Map.Entry<String, String> entry : tempMap.entrySet()) {
					if (!map.containsKey(entry.getKey())) {
						map.put(entry.getKey(), entry.getValue());
					}
				}
			}
			mav.addObject("oldColumnsMap", crudColumnMap);
		} else {
			// 判断报表来自哪里 TODO 以后再判断 现在默认是从mysql
			map = getColumnsFromDB(tableName);
			// map = hiveTableCommonRepository.queryColumns(tableName);
		}
		mav.addObject("columnsMap", map);
		mav.addObject("tableName", tableName);
		return mav;
	}

	private Map<String, String> getColumnsFromDB(String tableName) {
		Map<String, String> map = new HashMap<String, String>();
		ColumnsExample example = new ColumnsExample();
		example.setOrderByClause("ORDINAL_POSITION");
		example.createCriteria().andTableNameEqualTo(tableName);
		List<Columns> columnList = rCrudColumnService.selectByExampleWithoutBLOBs(example);
		if (CollectionUtils.isNotEmpty(columnList)) {

			for (Columns columns : columnList) {
				map.put(columns.getColumnName(), columns.getColumnName());
			}
		}

		return map;
	}

	@RequestMapping("/admin/report/saveHiveTalbeColumn.htm")
	public void submit(HttpServletRequest request, HttpServletResponse response) {
		String tableName = RequestUtil.getString(request, "tableName", "");
		JsonModel jsonModel = null;
		if (StringUtils.isBlank(tableName)) {
			jsonModel = JsonSupport.getJsonModel("", "2", "缺少表名", 0);
			this.doWriter(request, response, jsonModel);
			return;
		}
		List<RCrudColumn> allList = rCrudColumnCache.getAllListByKeyword(tableName);
		String[] columnIds = request.getParameterValues("columnId");
		String[] columnNames = request.getParameterValues("columnName");
		String[] columnSort = request.getParameterValues("columnSort");
		String[] listFlags = request.getParameterValues("listFlag");
		String[] queryFlags = request.getParameterValues("queryFlag");
		String[] columnTypes = request.getParameterValues("columnType");

		List<RCrudColumn> insertList = new ArrayList<RCrudColumn>();
		List<RCrudColumn> updateList = new ArrayList<RCrudColumn>();
		List<RCrudColumn> deleteList = new ArrayList<RCrudColumn>();

		if (columnIds == null || columnNames == null || columnSort == null || listFlags == null || queryFlags == null) {
			jsonModel = JsonSupport.getJsonModel("", "2", "缺少参数", 0);
			this.doWriter(request, response, jsonModel);
			return;
		}

		// 新增
		if (CollectionUtils.isEmpty(allList)) {
			for (int i = 0; i < columnIds.length; i++) {
				RCrudColumn rCrudColumn = new RCrudColumn();
				rCrudColumn.setColumnId(columnIds[i]);
				rCrudColumn.setColumnName(columnNames[i]);
				if (StringUtils.isNotBlank(columnSort[i])) {
					rCrudColumn.setSort(Integer.valueOf(columnSort[i]));
				}
				if (StringUtils.isNotBlank(listFlags[i])) {
					rCrudColumn.setListFlag(Integer.valueOf(listFlags[i]));
				}
				if (StringUtils.isNotBlank(queryFlags[i])) {
					rCrudColumn.setQueryFlag(Integer.valueOf(queryFlags[i]));
				}
				if (StringUtils.isNotBlank(columnTypes[i])) {
					rCrudColumn.setColumnType(Integer.valueOf(columnTypes[i]));
				} else {
					// 默认为文本
					rCrudColumn.setColumnType(1);
				}
				rCrudColumn.setKeyword(tableName);

				insertList.add(rCrudColumn);
			}
		} else {
			Map<String, RCrudColumn> tempMap = new HashMap<String, RCrudColumn>();
			for (RCrudColumn rCrudColumn : allList) {
				tempMap.put(rCrudColumn.getColumnId(), rCrudColumn);
				deleteList.add(rCrudColumn);
			}
			for (int i = 0; i < columnIds.length; i++) {
				RCrudColumn rCrudColumn = null;
				String key = columnIds[i];
				boolean isInsertFlag = true;
				if (tempMap.containsKey(key)) {
					rCrudColumn = tempMap.get(key);
					isInsertFlag = false;
					deleteList.remove(rCrudColumn);
				} else {
					rCrudColumn = new RCrudColumn();
				}
				rCrudColumn.setColumnId(key);
				rCrudColumn.setColumnName(columnNames[i]);
				if (StringUtils.isNotBlank(columnSort[i])) {
					rCrudColumn.setSort(Integer.valueOf(columnSort[i]));
				}
				if (StringUtils.isNotBlank(listFlags[i])) {
					rCrudColumn.setListFlag(Integer.valueOf(listFlags[i]));
				}
				if (StringUtils.isNotBlank(queryFlags[i])) {
					rCrudColumn.setQueryFlag(Integer.valueOf(queryFlags[i]));
				}
				if (StringUtils.isNotBlank(columnTypes[i])) {
					rCrudColumn.setColumnType(Integer.valueOf(columnTypes[i]));
				} else {
					// 默认为文本
					rCrudColumn.setColumnType(1);
				}
				if (isInsertFlag) {
					rCrudColumn.setKeyword(tableName);
					insertList.add(rCrudColumn);
				} else {
					updateList.add(rCrudColumn);
				}
			}
		}
		int rs = rCrudColumnService.save(insertList, updateList, deleteList);
		if (rs > 0) {
			rCrudColumnCache.refresh();
			jsonModel = JsonSupport.getJsonModel("", "0", "保存成功", 0);
		} else {
			jsonModel = JsonSupport.getJsonModel("", "1", "保存失败", 0);
		}
		this.doWriter(request, response, jsonModel);
	}

}
