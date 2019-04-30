package com.orange.commons.web.json;

import java.io.Serializable;

/**
 * 
 * 类JsonModel.java的实现描述：返回的json字符串的对象，如：CategoryModel，所对应的数据json格式为： {"num":1,
 * "data"
 * :[{"id":1,"parentId":0,"fullIdPath":"","categoryType":0,"fullNamePath":"",
 * "name":"测试类目",
 * "supportOrderOnline":true,"leaf":false,"hasSpu":true,"spell":"cslm"}],
 * "result":"1", "message":"出错提示信息"}
 * 
 * @author Administrator 2011-7-29 下午02:51:19
 */
public class JsonModel implements Serializable {

	private static final long	serialVersionUID	= -6443950120499240674L;

	public static String		RETURN_JSON_SUCCESS	= "1";
	public static String		RETURN_JSON_FAILURE	= "0";

	/**
	 * 数据的个数
	 */
	private String				num;

	/**
	 * 返回的json数据
	 */
	private Object				data;

	/**
	 * result:1表示成功，0表示失败
	 */
	private String				result;

	/**
	 * 出错提示信息
	 */
	private String				message;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
