/**
 *
 */
package com.orange.commons.web.json;

import net.sf.json.*;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 *
 */
@Deprecated
public class JsonSupport {

	static JsonConfig config = new JsonConfig();
	static {
		// 防止自包含
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		// 不过滤默认属性,例如class
		config.setIgnoreDefaultExcludes(false);
		// 当输出时间格式时，采用和JS兼容的格式输出
		config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
		config.setAllowNonStringKeys(true);
	}

	public static JSONObject getJSONObjet(Object o) {
		return JSONObject.fromObject(o, config);
	}

	@SuppressWarnings("unchecked")
	public static <T> T toBean(String json, Class<T> t) {
		JSONObject o = JSONObject.fromObject(json, config);
		return (T) JSONObject.toBean(o, t);
	}

	public static JSONArray getjsonArray(Object o) {
		return JSONArray.fromObject(o, config);
	}

	public static String getJSONString(Object o) {
		if (o instanceof Array || o instanceof List) {
			return JSONArray.fromObject(o, config).toString();
		} else {
			return JSONObject.fromObject(o, config).toString();
		}
	}

	/**
	 * 获取返回的信息。支持var和callback参数。
	 *
	 * @param data 数据
	 * @return 返回的信息
	 */
	public static String getReturnString(Object data, HttpServletRequest request) {
		JSON json = JSONSerializer.toJSON(data);
		String varName = StringEscapeUtils.escapeJavaScript(request.getParameter("var"));
		String functionName = StringEscapeUtils.escapeJavaScript(request.getParameter("callback"));
		StringBuilder sBuilder = new StringBuilder();

		// 如果存在参数var
		if (null != varName && varName.trim().length() > 0) {
			sBuilder.append("var ");
			sBuilder.append(varName);
			sBuilder.append(" = ");
			sBuilder.append(json.toString());
		}
		// 如果存在参数callback
		else if (null != functionName && functionName.trim().length() > 0) {
			sBuilder.append(functionName);
			sBuilder.append("(");
			sBuilder.append(json.toString());
			sBuilder.append(")");
		} else {
			sBuilder.append(json.toString());
		}
		return sBuilder.toString();
	}

	public static JSONObject readReqStr(HttpServletRequest request) {
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				// ignore
			}
		}
		return getJSONObjet(sb.toString());
	}
}
