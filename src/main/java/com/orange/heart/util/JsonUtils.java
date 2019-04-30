package com.orange.heart.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.orange.commons.web.json.JsonSupport;

import java.util.List;

/**
 * json工具类
 * 
 * @author yajun.wu
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonUtils {

	private static final String	YYYY_MM_DD_HH_MM_SS	= "yyyy-MM-dd HH:mm:ss";

	/**
	 * json字符串转化到类
	 * 
	 * @param jsonString json串
	 * @param c 要转化的类
	 * @return 转化后的类
	 */
	public static Object getObject(String jsonString, Class c) {

		try {
			Object obj = getGson().fromJson(jsonString, c);
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * json字符串转化到数组
	 * 
	 * @param jsonString json串
	 * @param t 类型
	 * @return 转化后的数组
	 */
	public static <T extends Object> List getList(String jsonString, TypeToken<T> t) {
		List<T> retList = getGson().fromJson(jsonString, t.getType());
		return retList;
	}

	/**
	 * 获取JsonString
	 * 
	 * @param o 要转化的对象
	 * @return
	 */
	public static String getJsonString(Object o) {
		return getGson().toJson(o);
	}

	private static Gson getGson() {
		Gson gson = new GsonBuilder().setDateFormat(YYYY_MM_DD_HH_MM_SS).serializeNulls().create();
		return gson;
	}

	/**
	 * json字符串转化到类 net.sf
	 * 
	 * @param jsonString json串
	 * @param c 要转化的类
	 * @return 转化后的类
	 */
	public static <T> T getObjectNetSf(String jsonString, Class c) {
		try {
			Object obj = JsonSupport.toBean(jsonString, c);
			return (T)obj;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

}
