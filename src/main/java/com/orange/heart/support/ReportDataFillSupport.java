package com.orange.heart.support;

import com.orange.heart.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReportDataFillSupport {

	/**
	 * 填充X坐标显示（每日）
	 * 
	 * @param fromList
	 * @param xaxisList
	 */
	public static List<List<String>> convertDailyXaxis(List<Map<String, Object>>... fromListArr) {
		List<List<String>> xaxisList = new ArrayList<List<String>>();
		Set<Integer> existSet = new HashSet<Integer>();
		for (List<Map<String, Object>> fromList : fromListArr) {
			for (Map<String, Object> subMap : fromList) {
				Date curDate;
				try {
					curDate = DateUtil.parseDate(String.valueOf(subMap.get("dt")), "yyyy-MM-dd");
				} catch (ParseException e) {
					continue;
				}

				int iDate = calcXDayInt(curDate);

				if (existSet.contains(iDate)) {
					continue;
				}
				existSet.add(iDate);

				String sDate = DateUtil.formatDate(curDate, "MM-dd");

				// X坐标描述
				List<String> xaxisTick = new ArrayList<String>();
				xaxisTick.add(String.valueOf(iDate));
				xaxisTick.add(sDate);
				xaxisList.add(xaxisTick);
			}
		}
		return xaxisList;
	}

	/**
	 * 填充数据（每日）
	 * 
	 * @param fromList
	 * @param toList
	 * @param valueName
	 */
	public static void fillDailyData(List<Map<String, Object>> fromList, List<List<Integer>> toList, String valueName) {
		for (Map<String, Object> subMap : fromList) {
			Date curDate;
			try {
				curDate = DateUtil.parseDate(String.valueOf(subMap.get("dt")), "yyyy-MM-dd");
			} catch (ParseException e) {
				continue;
			}
			int iDate = calcXDayInt(curDate);
			int value = new BigDecimal(String.valueOf(subMap.get(valueName))).intValue();
			// 交易金额
			List<Integer> pointList = new ArrayList<Integer>();
			pointList.add(iDate);
			pointList.add(value);
			toList.add(pointList);
		}
	}

	/**
	 * 获取日期对应的数字（每日）
	 * 
	 * @param date
	 * @return
	 */
	public static int calcXDayInt(Date date) {
		long min = date.getTime() / 1000;
		return Integer.valueOf(String.valueOf(min));
	}

	/**
	 * 填充数据（每日）
	 * 
	 * @param fromList
	 * @param dataMapList
	 * @param valueName
	 * @param label
	 */
	public static void fillDailyData(List<Map<String, Object>> fromList, List<Map<String, Object>> dataMapList,
			String valueName, String label) {
		List<List<Integer>> toList = new ArrayList<List<Integer>>();
		fillDailyData(fromList, toList, valueName);
		Map<String, Object> dataInfo = new LinkedHashMap<String, Object>();
		dataInfo.put("label", label);
		dataInfo.put("data", toList);
		dataMapList.add(dataInfo);
	}

	/**
	 * 填充X坐标显示（每月）
	 * 
	 * @param fromList
	 * @param xaxisList
	 */
	public static void fillMonthlyXaxis(List<Map<String, Object>> fromList, List<List<String>> xaxisList) {
		for (Map<String, Object> subMap : fromList) {
			Date curDate;
			try {
				curDate = DateUtil.parseDate(String.valueOf(subMap.get("dt")), "yyyy-MM");
			} catch (ParseException e) {
				continue;
			}
			int year = Integer.valueOf(DateUtil.formatDate(curDate, "yyyy"));
			int month = Integer.valueOf(DateUtil.formatDate(curDate, "MM"));
			int iDate = year * 12 + month;
			String sDate = DateUtil.formatDate(curDate, "yyyy-MM");

			// X坐标描述
			List<String> xaxisTick = new ArrayList<String>();
			xaxisTick.add(String.valueOf(iDate));
			xaxisTick.add(sDate);
			xaxisList.add(xaxisTick);
		}
	}

	/**
	 * 填充数据（每月）
	 * 
	 * @param fromList
	 * @param toList
	 * @param valueName
	 */
	public static void fillMonthlyData(List<Map<String, Object>> fromList, List<List<Integer>> toList, String valueName) {
		for (Map<String, Object> subMap : fromList) {
			Date curDate;
			try {
				curDate = DateUtil.parseDate(String.valueOf(subMap.get("dt")), "yyyy-MM");
			} catch (ParseException e) {
				continue;
			}
			int year = Integer.valueOf(DateUtil.formatDate(curDate, "yyyy"));
			int month = Integer.valueOf(DateUtil.formatDate(curDate, "MM"));
			int iDate = year * 12 + month;

			int money = new BigDecimal(String.valueOf(subMap.get(valueName))).intValue();
			// 交易金额
			List<Integer> pointList = new ArrayList<Integer>();
			pointList.add(iDate);
			pointList.add(money);
			toList.add(pointList);
		}
	}

	/**
	 * 填充数据（每月）
	 * 
	 * @param fromList
	 * @param dataMap
	 * @param valueName
	 * @param label
	 */
	public static void fillMonthlyData(List<Map<String, Object>> fromList, List<Map<String, Object>> dataMap,
			String valueName, String label) {
		List<List<Integer>> toList = new ArrayList<List<Integer>>();
		fillMonthlyData(fromList, toList, valueName);
		Map<String, Object> dataInfo = new LinkedHashMap<String, Object>();
		dataInfo.put("label", label);
		dataInfo.put("data", toList);
		dataMap.add(dataInfo);
	}

	/**
	 * 填充坐标为空的数据
	 * 
	 * @param cntMapList
	 * @param xaxisTicks
	 */
	public static void fillDataWithZero(List<Map<String, Object>> cntMapList, List<List<String>> xaxisTicks) {
		if (CollectionUtils.isNotEmpty(xaxisTicks) && CollectionUtils.isNotEmpty(cntMapList)) {
			for (Map<String, Object> subMap : cntMapList) {
				@SuppressWarnings("unchecked")
				List<List<Integer>> subDataList = (List<List<Integer>>) subMap.get("data");
				// 所有点都存在，则不处理
				if(subDataList.size() == xaxisTicks.size()){
					continue;
				}
				Map<Integer, List<Integer>> existPointMap = new HashMap<Integer, List<Integer>>();
				List<List<Integer>> newDataList = new ArrayList<List<Integer>>();
				Set<Integer> existSet = new HashSet<Integer>();
				for (List<Integer> pointList : subDataList) {
					existSet.add(pointList.get(0));
					existPointMap.put(pointList.get(0), pointList);
				}
				for (List<String> subXaxisList : xaxisTicks) {
					int iDate = Integer.valueOf(subXaxisList.get(0));
					if (!existSet.contains(iDate)) {
						List<Integer> pointList = new ArrayList<Integer>();
						pointList.add(iDate);
						pointList.add(0);
						newDataList.add(pointList);
						existSet.add(iDate);
					} else {
						newDataList.add(existPointMap.get(iDate));
					}
				}
				subMap.put("data", newDataList);
			}
		}
	}
}
