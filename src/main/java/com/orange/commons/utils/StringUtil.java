/**
 * 
 */
package com.orange.commons.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunjun
 * 
 */
public class StringUtil {

	public static boolean hasBlank(Object... objs) {
		for (Object obj : objs) {
			if (obj instanceof String) {
				if (StringUtils.isBlank((String) obj)) {
					return true;
				}
			} else if (obj instanceof Number) {
				if (((Number) obj).doubleValue() == 0) {
					return true;
				}
			} else {
				if (obj == null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 拆分字符串为数组
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static List<String> splitToList(String str, String split) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(split)) {
			String[] subs = str.split(split);
			for (String sub : subs) {
				list.add(sub);
			}
		}
		return list;
	}

	/**
	 * 把数组组合为字符串
	 * 
	 * @param strList
	 * @param split
	 * @return
	 */
	public static <T> String contactToString(List<T> strList, String split) {
		StringBuilder sBuilder = new StringBuilder();
		if (CollectionUtils.isNotEmpty(strList)) {
			for (T t : strList) {
				if (t == null || t.toString().length() == 0) {
					continue;
				}
				if (sBuilder.length() > 0) {
					sBuilder.append(split);
				}
				sBuilder.append(t);
			}
		}
		return sBuilder.toString();
	}

	/**
	 * 拆分字符串为数组
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static List<Integer> splitToIntegerList(String str, String split) {
		List<Integer> list = new ArrayList<Integer>();
		if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(split)) {
			String[] subs = str.split(split);
			for (String sub : subs) {
				list.add(Integer.valueOf(sub));
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param text1
	 * @param text2
	 * @return
	 */
	public static String nvl(String text1, String text2) {
		if (StringUtils.isNotBlank(text1)) {
			return text1;
		}
		return text2;
	}

	public static void main(String[] args) {
		int a = 01;
		double b = 01.0D;
		float c = 01.00F;
		short d = 01;
		System.out.println(hasBlank(a, b, c, d));
	}
}