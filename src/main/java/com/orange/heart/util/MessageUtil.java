package com.orange.heart.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MessageUtil {

	private static Map<String, Integer>	haveSendMessageMap	= new HashMap<String, Integer>();

	private static int					max					= 5;

	public static boolean isCanSend(String channel) {
		
		if (StringUtils.isBlank(channel)) {
			return false;
		}
		String key = channel + DateUtil.formatDateYmd(new Date());
		if (!haveSendMessageMap.containsKey(key)) {
			return true;
		}
		if (haveSendMessageMap.get(key) < max) {
			return true;
		}

		return false;
	}

	public static void set(String channel) {
		if (StringUtils.isBlank(channel)) {
			return;
		}
		String key = channel + DateUtil.formatDateYmd(new Date());
		if (haveSendMessageMap.containsKey(key)) {
			haveSendMessageMap.put(key, haveSendMessageMap.get(key) + 1);
		} else {
			haveSendMessageMap.put(key, 1);
		}
	}

}
