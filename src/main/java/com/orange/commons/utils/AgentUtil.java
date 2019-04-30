package com.orange.commons.utils;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author Administrator
 * 
 */
public class AgentUtil {

	// private static final Logger log = Logger.getLogger(AgentUtil.class);

	public static boolean isExit(String menuIds, String id) {
		if (StringUtils.isEmpty(menuIds) || StringUtils.isEmpty(id)) {
			return false;
		}

		String[] idArr = menuIds.split(",");
		if (idArr == null || idArr.length < 0) {
			return false;
		}

		for (int i = 0; i < idArr.length; i++) {
			if (ObjectUtils.equals(idArr[i], id)) {
				return true;
			}
		}
		return false;
	}
}
