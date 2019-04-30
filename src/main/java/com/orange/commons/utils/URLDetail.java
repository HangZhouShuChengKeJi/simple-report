package com.orange.commons.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * java.net.URL中，并没有分解请求参数，这个类就是把请求参数分解到map中，方便操作
 */
public class URLDetail {

	private URL					url;

	private Map<String, String>	queryMap;

	public URLDetail(String urlString) throws MalformedURLException {
		this.url = new URL(urlString);
		String queryString = getQueryString(urlString);
		queryMap = splitQuery(queryString);
	}

	private String getQueryString(String url) {
		Integer start = StringUtils.indexOf(url, "?");
		if (start > 0 && ((start + 1) < url.length())) {
			String queryString = StringUtils.substring(url, start + 1, url.length());
			return queryString;
		}
		return "";
	}

	public String toString() {
		StringBuilder ret = new StringBuilder(StringUtils.substringBefore(url.toString(), "?"));
		if (MapUtils.isNotEmpty(queryMap)) {
			ret.append("?");
			for (Map.Entry<String, String> item : queryMap.entrySet()) {
				try {
					ret.append(URLEncoder.encode(item.getKey(), "utf-8")).append("=").append(URLEncoder.encode(item.getValue(), "utf-8"))
							.append("&");
				} catch (UnsupportedEncodingException e) {
					// ignore
				}
			}
		}
		if (ret.charAt(ret.length() - 1) == '&') {
			ret.deleteCharAt(ret.length() - 1);
		}
		return ret.toString();
	}

	/**
	 * 将URL中的查询参数部分解析成键值对
	 *
	 * @param queryString URL中的查询参数部分，不含前缀'?'
	 * @return
	 */
	private static Map<String, String> splitQuery(String queryString) {
		final Map<String, String> queryPairs = new LinkedHashMap<String, String>();
		if (StringUtils.isBlank(queryString)) {
			return queryPairs;
		}

		final String[] pairs = queryString.split("&");
		for (String pair : pairs) {
			final int idx = pair.indexOf("=");
			try {
				String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
				String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
				if (!key.isEmpty()) {
					queryPairs.put(key, value);
				}
			} catch (UnsupportedEncodingException e) {
				// ignore
			}
		}
		return queryPairs;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Map<String, String> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, String> queryMap) {
		this.queryMap = queryMap;
	}

}
