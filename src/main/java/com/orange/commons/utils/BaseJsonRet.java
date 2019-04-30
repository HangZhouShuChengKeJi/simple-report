package com.orange.commons.utils;

import java.io.Serializable;

/**
 * SDK接口返回的父类
 * 
 * @author yajun
 */
public class BaseJsonRet implements Serializable {

	private static final long	serialVersionUID	= 3454966917740409703L;

	protected Integer			code;

	protected String			message;

	public BaseJsonRet() {
	}

	public BaseJsonRet(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
