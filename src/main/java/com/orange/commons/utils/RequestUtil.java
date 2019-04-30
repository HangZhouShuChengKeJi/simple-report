package com.orange.commons.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取属性的值的util
 * 
 * @author jianyang
 * 
 */
public class RequestUtil {

	public static String getString(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		return StringUtils.trim(value);
	}

	public static String getString(HttpServletRequest request, String name, String defaultValue) {
		String value = request.getParameter(name);
		if (value == null) {
			return defaultValue;
		}
		return StringUtils.trim(value);
	}

	public static int getInt(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		if (value != null && !"".equals(value)) {
			try {
				return Integer.parseInt(value.trim());
			} catch (Throwable t) {
				t.printStackTrace();
				return 0;
			}
		}
		return -1;
	}

	public static int getInt(HttpServletRequest request, String name, int defaultValue) {
		String value = request.getParameter(name);
		if (value != null && !"".equals(value)) {
			try {
				return Integer.parseInt(value.trim());
			} catch (Throwable t) {
				t.printStackTrace();
				return defaultValue;
			}
		}
		return defaultValue;
	}

	public static long getLong(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		if (value != null && !"".equals(value)) {
			return Long.parseLong(value.trim());
		}
		return -1L;
	}

	public static long getLong(HttpServletRequest request, String name, long defaultValue) {
		String value = request.getParameter(name);
		if (value != null && !"".equals(value)) {
			try {
				return Long.parseLong(value.trim());
			} catch (Throwable t) {
				t.printStackTrace();
				return defaultValue;
			}
		}
		return defaultValue;
	}

	public static double getDouble(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		if (value != null && !"".equals(value)) {
			return Double.parseDouble(value.trim());
		}
		return -1D;
	}

	public static double getDouble(HttpServletRequest request, String name, double defaultValue) {
		String value = request.getParameter(name);
		if (value != null && !"".equals(value)) {
			try {
				return Double.parseDouble(value.trim());
			} catch (Throwable t) {
				t.printStackTrace();
				return defaultValue;
			}
		}
		return defaultValue;
	}

	public static void main(String[] args) {
		System.out.println(Integer.valueOf("0330000"));
		try {
			System.out.println(Integer.parseInt("0330000"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
