/**
 * 
 */
package com.orange.heart.util;

import com.orange.commons.web.json.JsonModel;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
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
public class JsonSupport {

	static JsonConfig	config	= new JsonConfig();
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
	 * 获得返回的JSON信息
	 * 
	 * @param data 信息列表
	 * @return 返回的JSON信息
	 */
	public static JsonModel getJsonModel(List<?> data) {
		JsonModel model = new JsonModel();
		model.setResult(JsonModel.RETURN_JSON_SUCCESS);
		model.setData(data);
		model.setNum(null == data ? "0" : String.valueOf(data.size()));
		return model;
	}

	/**
	 * 获得返回的JSON信息
	 * 
	 * @author zhouhj 2011-08-02 11:21
	 * @param data 信息列表
	 * @param result 1表示成功，0表示失败
	 * @param message 出错信息
	 * @return 返回的JSON信息
	 */
	public static JsonModel getJsonModel(List<?> data, String result, String message) {
		JsonModel model = new JsonModel();
		model.setResult(result);
		model.setData(data);
		model.setNum(null == data ? "0" : String.valueOf(data.size()));
		model.setMessage(message);
		return model;
	}

	/**
	 * 获得返回的JSON信息,重载上面的方法
	 * 
	 * @param data 数据，兼容List、Set等集合类型
	 * @param result 1表示成功，0表示失败
	 * @param message 出错信息
	 * @return 返回的JSON信息
	 */
	public static JsonModel getJsonModel(Object data, String result, String message, int size) {
		JsonModel model = new JsonModel();
		model.setResult(result);
		model.setData(data);
		model.setNum(String.valueOf(size));
		model.setMessage(message);
		return model;
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
