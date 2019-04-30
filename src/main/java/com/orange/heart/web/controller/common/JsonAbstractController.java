/**
 * 
 */
package com.orange.heart.web.controller.common;

import com.orange.commons.web.json.JsonSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author allen
 * 
 */
public abstract class JsonAbstractController {

	/**
	 * 
	 * @param request
	 * @param response
	 */
	protected void doWriter(HttpServletRequest request, HttpServletResponse response, Object obj) {
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		String result = JsonSupport.getReturnString(obj, request);
		try {
			pw = response.getWriter();
			pw.println(result);

		} catch (Exception e) {
			pw.println("{\"result\":\"error\"}");
		}
		pw.flush();
		pw.close();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param jsonModel
	 */
	protected void doWriterScript(HttpServletRequest request, HttpServletResponse response, String script) {
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.println(script);

		} catch (Exception e) {
			pw.println("{\"result\":\"error\"}");
		}
		pw.flush();
		pw.close();
	}
}
