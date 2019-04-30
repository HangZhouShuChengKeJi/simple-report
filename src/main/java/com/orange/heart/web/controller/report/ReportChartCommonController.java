/**
 * 
 */
package com.orange.heart.web.controller.report;

import com.orange.commons.utils.RequestUtil;
import com.orange.commons.web.json.JsonSupport;
import com.orange.heart.constant.HeartConstant.CHART_PARAM_KEY;
import com.orange.heart.constant.HeartConstant.CHART_X_INFO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 图表展示
 * 
 */
@Controller
public class ReportChartCommonController {

	@RequestMapping("/house/commonchart.htm")
	public ModelAndView reportChart(HttpServletRequest request, HttpServletResponse response) {

		// Map<String, List<Map<String, String>>> map = new HashMap<String,
		// List<Map<String, String>>>();
		//
		// List<Map<String, String>> ssList = new ArrayList<Map<String,
		// String>>();
		//
		// for (int i = 0; i < 10; i++) {
		// Map<String, String> sssMap = new HashMap<String, String>();
		// sssMap.put(CHART_X_INFO.X_KEY, "a" + (i + 1));
		// sssMap.put(CHART_X_INFO.X_KEY_VALUE, String.valueOf((int)
		// (Math.random() * 1000)));
		// ssList.add(sssMap);
		// }
		//
		// List<Map<String, String>> ssList2 = new ArrayList<Map<String,
		// String>>();
		//
		// for (int i = 0; i < 10; i++) {
		// Map<String, String> sssMap = new HashMap<String, String>();
		// sssMap.put(CHART_X_INFO.X_KEY, "a" + (i + 1));
		// sssMap.put(CHART_X_INFO.X_KEY_VALUE, String.valueOf((int)
		// (Math.random() * 1000)));
		// ssList2.add(sssMap);
		// }
		//
		// map.put("若愚", ssList);
		// map.put("若愚2", ssList2);
		ModelAndView mav = new ModelAndView("/screen/warehouse/report/commonchart");

		mav.addObject(CHART_PARAM_KEY.HTML_DATAINFO, RequestUtil.getString(request, CHART_PARAM_KEY.HTML_DATAINFO));
		mav.addObject(CHART_PARAM_KEY.HTML_XAXISTICKS, RequestUtil.getString(request, CHART_PARAM_KEY.HTML_XAXISTICKS));

		mav.addObject(CHART_PARAM_KEY.HTML_TITLE, RequestUtil.getString(request, CHART_PARAM_KEY.HTML_TITLE, "图表"));
		return mav;
	}
	
	public static void dddd(){
		
	}

	public static void setChartValue(ModelAndView mav, Map<String, List<Map<String, String>>> map) {

		if (map == null || mav == null) {
			return;
		}

		List<Map<String, Object>> dataMapList = new ArrayList<Map<String, Object>>();
		List<List<String>> xaxisTicks = new ArrayList<List<String>>();

		Map<Integer, List<String>> xShowMap = new HashMap<Integer, List<String>>();
		for (Map.Entry<String, List<Map<String, String>>> entry : map.entrySet()) {
			Map<String, Object> dataInfo = new LinkedHashMap<String, Object>();
			List<List<String>> chartList = new ArrayList<List<String>>();
			dataInfo.put("label", entry.getKey());

			int index = 1;
			for (Map<String, String> subMap : entry.getValue()) {
				// 数据点坐标
				if (StringUtils.isBlank(subMap.get(CHART_X_INFO.X_KEY))) {
					index++;
					continue;
				}
				index++;
				List<String> pointList = new ArrayList<String>();
				// X坐标显示值
				pointList.add(String.valueOf(index));
				pointList.add(subMap.get(CHART_X_INFO.X_KEY_VALUE));
				chartList.add(pointList);

				if (!xShowMap.containsKey(index)) {
					List<String> xaxisTick = new ArrayList<String>();
					// X坐标描述
					xaxisTick.add(String.valueOf(index));
					xaxisTick.add(subMap.get(CHART_X_INFO.X_KEY));
					xShowMap.put(index, xaxisTick);
				}
			}
			dataInfo.put("data", chartList);
			dataMapList.add(dataInfo);
		}

		for (Map.Entry<Integer, List<String>> entry : xShowMap.entrySet()) {
			xaxisTicks.add(entry.getValue());
		}
		mav.addObject("html_dataInfo", JsonSupport.getJSONString(dataMapList));
		mav.addObject("html_xaxisTicks", JsonSupport.getJSONString(xaxisTicks));
		System.out.println(JsonSupport.getJSONString(dataMapList));
		System.out.println(JsonSupport.getJSONString(xaxisTicks));
	}

}
