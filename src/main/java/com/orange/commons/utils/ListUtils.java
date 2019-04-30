package com.orange.commons.utils;

import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListUtils {

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> listToMap(List<V> list, Class<K> clzK, String keyName) {
		Map<K, V> resultMap = new LinkedHashMap<K, V>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (V bean : list) {
				try {
					String methodName = "get" + keyName.substring(0, 1).toUpperCase() + keyName.substring(1);
					Method method = bean.getClass().getMethod(methodName);
					K key = (K) method.invoke(bean);
					resultMap.put(key, bean);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return resultMap;
	}
}
