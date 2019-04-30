package com.orange.heart.service;

import java.util.List;
import java.util.Map;


public interface PubPayMethodService {
	
	/**
	 * 获取通道OA名称
	 * */
	Map<String, String>  getPubPayMethodByOAName(List<String> methodList);
	
}
