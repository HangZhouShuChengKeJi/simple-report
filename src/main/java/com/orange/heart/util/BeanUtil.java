package com.orange.heart.util;

import com.orange.commons.utils.PageInfo;
import com.orange.commons.utils.RequestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil {

	private static Map<Class<?>, Field[]>	map	= new HashMap<Class<?>, Field[]>();

	private static Field[]					pageInfoFields;

	public static <T> T setBeanValue(ResultSet rs, T t) {
		if (t == null) {
			return t;
		}
		Class<?> v = t.getClass();
		Field[] fArr = map.get(v);
		if (fArr == null) {
			fArr = v.getDeclaredFields();
			map.put(v, fArr);
		}

		for (int i = 0; i < fArr.length; i++) {
			Field f = fArr[i];
			Type type = f.getGenericType();
			String fieldName = f.getName();
			String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Method m = v.getMethod("set" + name, f.getType());
				if (type.equals(Integer.class)) {
					m.invoke(t, rs.getInt(fieldName));
				} else if (type.equals(String.class)) {
					m.invoke(t, rs.getString(fieldName));
				}
			} catch (Exception e) {
			}
		}
		return t;
	}

	/**
	 * 从request获取值
	 * 
	 * @param request
	 * @param t
	 */
	public static <T extends PageInfo> void setBeanRequestValue(HttpServletRequest request, T t) {
		if (t == null) {
			return;
		}
		Class<?> v = t.getClass();
		Field[] fArr = map.get(v);
		if (fArr == null) {
			fArr = v.getDeclaredFields();
			map.put(v, fArr);
		}
		// 子类的值
		setParamValueFromRequest(request, t, v, fArr);
		// 父类的值
		if (pageInfoFields == null) {
			pageInfoFields = v.getSuperclass().getDeclaredFields();
		}
		setParamValueFromRequest(request, t, v, pageInfoFields);

	}

	/**
	 * 从request获取值
	 * 
	 * @param request
	 * @param t
	 * @param v
	 * @param fArr
	 */
	private static <T> void setParamValueFromRequest(HttpServletRequest request, T t, Class<?> v, Field[] fArr) {
		for (int i = 0; i < fArr.length; i++) {
			Field f = fArr[i];
			Type type = f.getGenericType();
			String fieldName = f.getName();
			String requestValue = RequestUtil.getString(request, fieldName, "");
			if (StringUtils.isBlank(requestValue)) {
				continue;
			}
			String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Method m = v.getMethod("set" + name, f.getType());
				if (type.equals(Integer.class)) {
					m.invoke(t, Integer.valueOf(requestValue));
				} else if (type.equals(String.class)) {
					m.invoke(t, requestValue);
				}
			} catch (Exception e) {
			}
		}
	}

	public static <T extends PageInfo> String createBeanSql(T t) {
		StringBuffer sb = new StringBuffer();
		if (t == null) {
			return sb.toString();
		}
		Class<?> v = t.getClass();
		Field[] fArr = map.get(v);
		if (fArr == null) {
			fArr = v.getDeclaredFields();
			map.put(v, fArr);
		}

		sqlWhere(t, v, fArr, sb);

		// 防止 limit为空值
		t.setPageNo(t.getPageNo());
		sb.append(" limit ");
		if (t.getOffset() != 0) {
			sb.append(t.getOffset()).append(",");
		}

		sb.append(t.getLimit());
		// sqlWhere(t, v, pageInfoFields, sb);
		System.out.println(sb.toString());
		return sb.toString();
	}

	private static <T> void sqlWhere(T t, Class<?> v, Field[] fArr, StringBuffer sb) {
		StringBuffer tempSb = new StringBuffer();
		for (int i = 0; i < fArr.length; i++) {
			Field f = fArr[i];
			String fieldName = f.getName();
			String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Method m = v.getMethod("get" + name);
				Object o = m.invoke(t);
				if (o != null) {
					if (tempSb.length() != 0) {
						tempSb.append(" and ");
					}
					tempSb.append(fieldName).append(" = ").append(o);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (tempSb.length() != 0) {
			sb.append(" where ").append(tempSb.toString());
		}
	}

	public static <T> List<T> setBeanValueFromMap(List<Map<String, Object>> rs, final Class<T> beanClass) {
		List<T> list = new ArrayList<T>();
		if (CollectionUtils.isEmpty(rs)) {
			return list;
		}

		Field[] fArr = map.get(beanClass);
		if (fArr == null) {
			fArr = beanClass.getDeclaredFields();
			map.put(beanClass, fArr);
		}

		for (Map<String, Object> map : rs) {
			try {
				T t = beanClass.newInstance();
				for (int i = 0; i < fArr.length; i++) {
					Field f = fArr[i];
					String fieldName = f.getName();
					String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

					Object o = map.get(fieldName);
					if (o == null) {
						continue;
					}
					Type type = f.getGenericType();
					Method m = beanClass.getMethod("set" + name, f.getType());
					try {
						if (type.equals(Integer.class)) {
							m.invoke(t, Integer.valueOf(String.valueOf(o)));
						} else if (type.equals(BigDecimal.class)) {
							m.invoke(t, new BigDecimal(String.valueOf(String.valueOf(o))));
						} else if (type.equals(Date.class)) {
							m.invoke(t, DateUtil.parseDateYmdHms(String.valueOf(o)));
						} else if (type.equals(String.class)) {
							m.invoke(t, String.valueOf(o));
						} else {
							m.invoke(t, o);
						}
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}

				}
				list.add(t);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return list;
	}



}
