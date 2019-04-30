package com.orange.heart.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtils {

	private final static String	defEncode	= "UTF-8";

	private static final Log log = LogFactory.getLog(CookieUtils.class);

	/**
	 * 取Cookie
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		return getCookieValue(request, name, defEncode);
	}

	private static String getCookiePriValue(HttpServletRequest request, String name) {
		Cookie sCookie = getCookie(request, name);
		if (sCookie == null)
			return null;
		return sCookie.getValue();
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null && null != name) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equals(cookies[i].getName())) {
					return cookies[i];
				}
			}
		}
		return null;
	}

	public static String getCookieValue(HttpServletRequest request, String name, String encode) {
		if (encode == null || "".equals(encode.trim())) {
			encode = defEncode; // 默认"UTF-8"
		}
		String val = getCookiePriValue(request, name);
		if (val == null)
			return null;
		try {
			return URLDecoder.decode(val, encode);
		} catch (UnsupportedEncodingException e) {
			log.error("decode cookie value failed, val=" + val, e);
			return val;
		}
	}

	/**
	 * 清除Cookie(清空Cookie值，使立即生效)
	 */
	public static void clearCookie(HttpServletResponse response, String name) {
		setCookie(response, name, "");
	}

	/**
	 * 删除Cookie(设置成空串，使立即清空)
	 */
	public static void removeCookie(HttpServletResponse response, String name) {
		if (name == null) {
			return;
		}
		Cookie cookie = new Cookie(name, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public static void removeCookie(HttpServletResponse response, String name, String domain, String path) {
		if (name == null) {
			return;
		}
		Cookie killMyCookie = new Cookie(name, "");
		killMyCookie.setMaxAge(0);
		killMyCookie.setDomain(domain);
		killMyCookie.setPath(path);
		response.addCookie(killMyCookie);
	}

	public static void removeCookie(HttpServletResponse response, String name, String path) {
		if (name == null) {
			return;
		}
		Cookie killMyCookie = new Cookie(name, "");
		killMyCookie.setMaxAge(0);
		killMyCookie.setPath(path);
		response.addCookie(killMyCookie);
	}

	/**
	 * 删除所有Cookie
	 */
	public static void removeAllCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || cookies.length <= 0)
			return;
		for (int i = 0; i < cookies.length; i++) {
			Cookie sCookie = cookies[i];
			if (sCookie != null) {
				sCookie.setValue(null);
				sCookie.setPath("/");
				sCookie.setMaxAge(0);
				response.addCookie(sCookie);
			}
		}
	}

	/**
	 * 设置Cookie
	 */
	public static Cookie cookie(String name, String value) {
		Cookie _cookie;
		try {
			if (null == value) {
				_cookie = new Cookie(name, value);
			} else {
				_cookie = new Cookie(name, URLEncoder.encode(value, defEncode));
			}
		} catch (UnsupportedEncodingException e) {
			_cookie = new Cookie(name, value);
			log.error("decode cookie value failed, value=" + value, e);
		} catch (Exception e) {
			_cookie = new Cookie(name, value);
			log.error("decode cookie value failed, value=" + value, e);
		}
		return _cookie;
	}

	/**
	 * 设置为负值，为浏览器进程Cookie(内存中保存)，关闭浏览器就失效 设置session时需要
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		if (name == null) {
			return;
		}
		Cookie _cookie = cookie(name, value);
		_cookie.setMaxAge(-1); // 设置为负值
		_cookie.setPath("/");
		response.addCookie(_cookie);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, int expiry) {
		Cookie _cookie = cookie(name, value);
		_cookie.setMaxAge(expiry);// how many seconds
		_cookie.setPath("/");
		response.addCookie(_cookie);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, int expiry, String domain,
                                 String path) {
		if (name == null) {
			return;
		}
		Cookie _cookie = cookie(name, value);
		_cookie.setMaxAge(expiry);// how many seconds
		_cookie.setDomain(domain);
		_cookie.setPath(path);
		response.addCookie(_cookie);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, int expiry, String path) {
		if (name == null) {
			return;
		}
		Cookie _cookie = cookie(name, value);
		_cookie.setMaxAge(expiry);// how many seconds
		_cookie.setPath(path);
		response.addCookie(_cookie);
	}

	public static void setCookie(HttpServletResponse response, Cookie cookie) {
		response.addCookie(cookie);
	}

	public static String getDomain(HttpServletRequest request) {
		// return request.getHeader("Host");
		return getDomainWithoutPort(request);
	}

	/**
	 * 从Http头中获取请求源域名(去除端口)
	 * 
	 * @author st0xff
	 * @param req
	 * @return
	 */
	static String getDomainWithoutPort(HttpServletRequest req) {
		String domainString = req.getHeader("Host");
		int dotIndex = domainString.indexOf(':');
		return dotIndex > 0 ? domainString.substring(0, dotIndex) : domainString;

	}

}
