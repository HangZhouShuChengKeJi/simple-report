package com.orange.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ResourceBundle;

/**
 * 读取应用配置的类
 * 
 * @author wuyajun
 */
public final class ResourceUtil {

	private final static Log		log	= LogFactory.getLog(ResourceUtil.class);

	private static ResourceBundle	system;
	static {
		try {
			system = ResourceBundle.getBundle("systemConfig");
		} catch (Exception e) {
			log.error("systemConfig.properties Not Found. some keys lost.");
		}
	}

	/**
	 * systemConfig
	 * 
	 * @param key
	 * @return
	 */
	public static String getSystem(final String key) {
		String msg = null;
		try {
			msg = system.getString(key);
		} catch (Exception e) {
			log.error("Key['" + key + "'] Not Found in systemConfig.properties .");
		}
		return msg == null ? "" : msg;
	}

	private static ResourceBundle	url;
	static {
		try {
			url = ResourceBundle.getBundle("urlConfig");
		} catch (Exception e) {
			log.error("urlConfig.properties Not Found. some keys lost.");
		}
	}

	public static WebServer getUrl(final String key) {
		String msg = null;
		try {
			msg = url.getString(key);
		} catch (Exception e) {
			log.error("Key['" + key + "'] Not Found in urlConfig.properties .");
		}
		return null == msg ? new WebServer(url.getString("default")) : new WebServer(msg);
	}

	public static WebServer getUrl(final String key, final String ad) {
		String msg = null;
		try {
			msg = url.getString(key);
		} catch (Exception e) {
			log.error("Key['" + key + "'] Not Found in urlConfig.properties .");
		}
		String result = null;
		if (null == msg) {
			result = StringUtils.isBlank(ad) ? url.getString("default") : url.getString("default") + ad;
		} else {
			result = StringUtils.isBlank(ad) ? msg : msg + ad;
		}
		return new WebServer(result);
	}

	public static class WebServer {

		public WebServer(String domain) {
			this.domain = domain;
		}

		private String	domain;

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

		public String queryData(String param, String value) {
			if (StringUtils.isBlank(this.domain))
				return "";
			return (this.domain.lastIndexOf("?") > 0) ? (this.domain + "&" + param + "=" + value) : (this.domain + "?"
					+ param + "=" + value);
		}

		@Override
		public String toString() {
			return (null == this.domain || "".equals(this.domain)) ? "/" : this.domain;
		}
	}

}
