package com.orange.heart.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * FIXME 正式上线之后，这个地方要修改 测试环境工具类
 * 
 * @author yajun
 * 
 */
public class TestEnvUtil {

	public static final Set<Integer>	testUserIds	= new HashSet<Integer>();
	static {
		testUserIds.add(2183);
		testUserIds.add(1371);
		testUserIds.add(1665);
	}

	/**
	 * 当前是否是测试环境
	 */
	public static boolean isTestEnv() {
		String catalinaHome = System.getProperty("catalina.home");
		if (StringUtils.contains(catalinaHome, "tomcat_test")) {
			return true;
		}
		if (!StringUtils.contains(catalinaHome, "/usr/local/tomcat")
				&& !StringUtils.contains(catalinaHome, "/home/admin/tomcat")
				&& !StringUtils.contains(catalinaHome, "/home/admin/tomcat_install")) {
			return true;
		}
		return false;
	}

	public static Set<Integer> getTestUserId() {
		return testUserIds;
	}

}
