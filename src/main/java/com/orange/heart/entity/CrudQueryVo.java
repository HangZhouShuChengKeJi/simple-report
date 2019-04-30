/**
 * 
 */
package com.orange.heart.entity;

import java.util.List;

public class CrudQueryVo extends RCrudColumn {
	private static final long	serialVersionUID	= -6905437613057647141L;

	private String isShow;

	private String inputValue;

	private List<String> inputValueList;

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public List<String> getInputValueList() {
		return inputValueList;
	}

	public void setInputValueList(List<String> inputValueList) {
		this.inputValueList = inputValueList;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

}
