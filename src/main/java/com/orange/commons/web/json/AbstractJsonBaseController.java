package com.orange.commons.web.json;

import net.sf.json.JSONSerializer;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class AbstractJsonBaseController {

	public static final String	RESPONSE_TEXT	= "responseText";

	public static final String	ERRORS			= "errors";

	protected String getJson(Map<String, Object> data) {
		return JSONSerializer.toJSON(data).toString();
	}

	protected String getSuccessJsonString() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("result", "success");
		return getJson(data);
	}

	protected String getSuccessJsonString(Map<String, Object> ret) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("result", "success");
		data.put("data", ret);
		return getJson(data);
	}

	protected String getSuccessJsonString(String msg) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("result", "success");
		data.put("msg", msg);
		return getJson(data);
	}

	protected String getFailJsonString(String failMsg) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("result", "false");
		data.put("msg", failMsg);
		return getJson(data);
	}

	/**
	 * 写入一个错误信息
	 * 
	 * @see ShowUtil
	 */
	@SuppressWarnings("unchecked")
	protected ModelAndView addFieldError(ModelAndView mav, String field, String error) {
		if (mav == null || StringUtils.isBlank(field) || StringUtils.isBlank(error)) {
			return mav;
		}
		Map<String, String> errorModel = (Map<String, String>) mav.getModel().get(ERRORS);
		if (errorModel == null) {
			errorModel = new HashMap<String, String>();
			mav.getModel().put(ERRORS, errorModel);
		}
		errorModel.put(field, error);
		return mav;
	}

}
