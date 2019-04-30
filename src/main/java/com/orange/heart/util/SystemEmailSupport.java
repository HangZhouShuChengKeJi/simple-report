package com.orange.heart.util;

import com.orange.commons.utils.ResourceUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Administrator
 * 
 */
public class SystemEmailSupport {

	private static String						email				= ResourceUtil.getSystem("email");
	private static List<String>					emailList			= new ArrayList<String>();
	private static String[]						allEmails			= null;
	private static String[]						allChannelEmails	= new String[] {};
	private static String						allEmail			= ResourceUtil.getSystem("allEmail");

	private static List<String>					channelMobile		= new ArrayList<String>();

	private static Map<String, List<String>>	keyValueListMap		= new HashMap<String, List<String>>();

	static {
		String[] emailArr = email.split(";");
		for (String emailSingle : emailArr) {
			if (StringUtils.isNotBlank(emailSingle)) {
				emailList.add(emailSingle.trim());
			}
		}
		if (StringUtils.isNotBlank(allEmail)) {
			allEmails = allEmail.split(";");
		}
		String allChanndelEmails = ResourceUtil.getSystem("channelEmail");
		if (StringUtils.isNotBlank(allChanndelEmails)) {
			allChannelEmails = allChanndelEmails.split(";");
		}
		String s = ResourceUtil.getSystem("channelMobile");
		if (StringUtils.isNotBlank(s)) {
			String[] ss = s.split(";");
			for (int i = 0; i < ss.length; i++) {
				channelMobile.add(ss[i]);
			}
		}

	}

	/**
	 * 获取邮箱帐号
	 * 
	 * @return
	 */
	public static String getEmail() {
		Random random = new Random();
		int idx = random.nextInt(emailList.size());
		return emailList.get(idx);
	}

	/**
	 * 获取邮箱帐号
	 * 
	 * @return
	 */
	public static String[] getAllEmail() {
		return allEmails;
	}

	/**
	 * 获取通道异常通知邮箱帐号
	 * 
	 * @return
	 */
	public static String[] getAllChannelEmail() {
		return allChannelEmails;
	}

	/**
	 * 获取通道异常通知手机号码
	 * 
	 * @return
	 */
	public static List<String> getChannelMobile() {
		return channelMobile;
	}

	/**
	 * 获取配置的参数值
	 * @param key
	 * @return
	 */
	public static List<String> getListValue(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		if (keyValueListMap.containsKey(key)) {
			return keyValueListMap.get(key);
		} else {
			List<String> tempList = new ArrayList<String>();
			String valueStr = ResourceUtil.getSystem(key);
			if (StringUtils.isNotBlank(valueStr)) {
				String[] ss = valueStr.split(";");

				for (int i = 0; i < ss.length; i++) {
					tempList.add(ss[i]);
				}

			}
			keyValueListMap.put(key, tempList);
			return tempList;
		}
	}
}
