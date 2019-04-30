package com.orange.heart.util;

import com.orange.heart.constant.HeartConstant.CHART_PARAM_KEY;
import com.orange.heart.constant.HeartConstant.CHART_X_INFO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChartUtil {

	private static Map<Class<?>, Map<String, Field>>	map	= new HashMap<Class<?>, Map<String, Field>>();

	public static <T> Map<String, List<Map<String, String>>> listBeanToChartValue(List<T> list, String xShow,
			Map<String, String> chartMap) {
		Map<String, List<Map<String, String>>> resultMap = new HashMap<String, List<Map<String, String>>>();
		if (list == null || StringUtils.isBlank(xShow) || chartMap == null) {
			return resultMap;
		}
		Class<?> v = list.get(0).getClass();
		Map<String, Field> fMap = map.get(v);
		if (fMap == null) {
			Field[] fArr = v.getDeclaredFields();
			fMap = new HashMap<String, Field>();
			for (int i = 0; i < fArr.length; i++) {
				fMap.put(fArr[i].getName(), fArr[i]);
			}
			map.put(v, fMap);
		}
		Field xf = fMap.get(xShow);
		if (xf == null) {
			return resultMap;
		}

		for (Map.Entry<String, String> entry : chartMap.entrySet()) {
			String key = entry.getKey();
			String fieldName = entry.getValue();
			if (StringUtils.isBlank(key) || StringUtils.isBlank(fieldName)) {
				continue;
			}
			Field f = fMap.get(fieldName);
			if (f == null) {
				continue;
			}
			List<Map<String, String>> ssList = new ArrayList<Map<String, String>>();
			for (T t : list) {
				try {
					Map<String, String> sssMap = new HashMap<String, String>();
					String xName = xShow.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Method m;
					m = v.getMethod("get" + xName, xf.getType());
					Object o = m.invoke(t);
					sssMap.put(CHART_X_INFO.X_KEY, String.valueOf(o));

					String vName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

					Method vm = v.getMethod("get" + vName, f.getType());
					Object ov = vm.invoke(t);
					// 这个值一定要是数字，否则图表不展示
					sssMap.put(CHART_X_INFO.X_KEY_VALUE, String.valueOf(ov));
					ssList.add(sssMap);
				} catch (Exception e) {
					continue;
				}
			}
			resultMap.put(key, ssList);
		}

		return resultMap;
	}

	public static Map<String, List<Map<String, String>>> listMapToChartValue(List<Map<String, Object>> list,
			String xShow, Map<String, String> chartMap) {
		Map<String, List<Map<String, String>>> resultMap = new HashMap<String, List<Map<String, String>>>();
		if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(xShow) || chartMap == null) {
			return resultMap;
		}
		Class<?> v = list.get(0).getClass();
		Map<String, Field> fMap = map.get(v);
		if (fMap == null) {
			Field[] fArr = v.getDeclaredFields();
			fMap = new HashMap<String, Field>();
			for (int i = 0; i < fArr.length; i++) {
				fMap.put(fArr[i].getName(), fArr[i]);
			}
			map.put(v, fMap);
		}

		for (Map.Entry<String, String> entry : chartMap.entrySet()) {
			String key = entry.getKey();
			String fieldName = entry.getValue();
			if (StringUtils.isBlank(key) || StringUtils.isBlank(fieldName)) {
				continue;
			}
			List<Map<String, String>> ssList = new ArrayList<Map<String, String>>();
			for (Map<String, Object> t : list) {

				try {
					Map<String, String> sssMap = new HashMap<String, String>();
					sssMap.put(CHART_X_INFO.X_KEY, String.valueOf(t.get(xShow)));
					// 这个值一定要是数字，否则图表不展示
					sssMap.put(CHART_X_INFO.X_KEY_VALUE, String.valueOf(t.get(fieldName)));
					ssList.add(sssMap);
				} catch (Exception e) {
					continue;
				}

			}
			resultMap.put(key, ssList);
		}

		return resultMap;
	}

	public static Map<String, String> assembleChartValue(Map<String, List<Map<String, String>>> map) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (map == null) {
			return resultMap;
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

		
		resultMap.put(CHART_PARAM_KEY.HTML_DATAINFO, JsonUtils.getJsonString(dataMapList));
		resultMap.put(CHART_PARAM_KEY.HTML_XAXISTICKS, JsonUtils.getJsonString(xaxisTicks));
		return resultMap;
	}

	public static Map<String, String> assembleChartValue(List<Map<String, Object>> list, String xShow,
			Map<String, String> chartMap) {
		return assembleChartValue(listMapToChartValue(list, xShow, chartMap));
	}

	public static Map<String, String> assembleChartValue(ModelAndView mav, List<Map<String, Object>> list,
                                                         String xShow, Map<String, String> chartMap) {
		Map<String, String> map = assembleChartValue(listMapToChartValue(list, xShow, chartMap));
		if (mav != null && map != null) {
			mav.addObject(CHART_PARAM_KEY.HTML_DATAINFO, map.get(CHART_PARAM_KEY.HTML_DATAINFO));
			mav.addObject(CHART_PARAM_KEY.HTML_XAXISTICKS, map.get(CHART_PARAM_KEY.HTML_XAXISTICKS));
		}
		return map;
	}

}
