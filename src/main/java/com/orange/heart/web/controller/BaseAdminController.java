/**
 * 
 */
package com.orange.heart.web.controller;

import com.orange.commons.cache.MemcachedTemplate;
import com.orange.heart.web.controller.common.JsonAbstractController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author allen
 *
 */
public class BaseAdminController extends JsonAbstractController{
	
	@Resource
	protected MemcachedTemplate	memcachedTemplate;

	
	protected Integer getCurUserId(HttpServletRequest request) {
//		String sessionId = CookieUtils.getCookieValue(request, WebgameConstant.COOKIE.ADMIN_SESSION_ID);
//		if (StringUtils.isBlank(sessionId)) {
//			return null;
//		}
//		Integer userId = (Integer) memcachedTemplate.get(WarehouseInterceptor.getMemcacheUserIdKey(sessionId));
//		return userId;
		return 0;
	}
}
