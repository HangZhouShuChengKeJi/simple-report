package com.orange.heart.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 用于Excel的各类操作
 * 
 */
public interface PoiService {

	/**
	 * 在内存中生成Excel
	 * 
	 * @param list
	 * @param columnList
	 * @param columnNameList
	 * @return
	 */
	 HSSFWorkbook getQueryResultExcel(List<Map<String, Object>> list, List<String> columnList, List<String> columnNameList);

	/**
	 * 输入Excel
	 * @param response
	 * @param wb
	 */
	void writeToClient(HttpServletResponse response, HSSFWorkbook wb);


}
