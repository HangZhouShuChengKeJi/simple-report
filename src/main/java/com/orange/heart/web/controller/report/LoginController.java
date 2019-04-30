package com.orange.heart.web.controller.report;

import com.orange.commons.cache.MemcachedTemplate;
import com.orange.commons.utils.JBPEncoder;
import com.orange.commons.utils.RanGenUtil;
import com.orange.commons.utils.RequestUtil;
import com.orange.commons.web.json.JsonModel;
import com.orange.heart.constant.HeartConstant.HEART_LOGIN_CONSTANTS;
import com.orange.heart.entity.RUser;
import com.orange.heart.service.RUserService;
import com.orange.heart.util.CookieUtils;
import com.orange.heart.util.JsonSupport;
import com.orange.heart.web.controller.common.JsonAbstractController;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 *
 * @author yeyj
 */
@Controller
public class LoginController extends JsonAbstractController {

    @Resource
    private MemcachedTemplate memcachedTemplate;

    @Resource
    private RUserService rUserService;


    @RequestMapping("/report/login.htm")
    public String test(Model model, HttpServletRequest request) {
        String doneURL = RequestUtil.getString(request, "doneURL");
        request.setAttribute("doneURL", doneURL);
        return "/screen/heart/login";
    }

    /**
     * 登出
     *
     * @returnÓ
     */
    @RequestMapping("/report/logout.htm")
    public String logout(HttpServletRequest request, Model model) {
        request.getSession().removeAttribute(HEART_LOGIN_CONSTANTS.COOKIE_HEART_SESSION_ID);
        String sessionId = CookieUtils.getCookieValue(request, HEART_LOGIN_CONSTANTS.COOKIE_HEART_USER_NICK);
        if (StringUtils.isNotBlank(sessionId)) {
            memcachedTemplate.delete(sessionId);
        }
        return "redirect:/report/login.htm";
    }

    @RequestMapping("/report/login/loginSubmit.htm")
    public void loginSubmit(HttpServletRequest request, HttpServletResponse response) {

        String account = RequestUtil.getString(request, "userName");
        String password = RequestUtil.getString(request, "password");
        JsonModel jsonModel = null;
        RUser rUser = rUserService.getUserByMobile(account);
        if (rUser == null) {
            rUser = rUserService.getUserByEmail(account);
        }
        if (null == rUser) {
            jsonModel = JsonSupport.getJsonModel("", "2", "用户不存在！", 0);
        } else {
            String passwdMD5 = JBPEncoder.MD5Encoder(password);
            if (!StringUtils.equals(passwdMD5, rUser.getLoginPwd())) {
                jsonModel = JsonSupport.getJsonModel("", "3", "密码不正确！", 0);
            } else {
                jsonModel = JsonSupport.getJsonModel("", "success", "用户登陆成功！", 0);
                setAfterLogin(request, response, rUser);
            }
        }

        doWriter(request, response, jsonModel);

    }


    @SuppressWarnings("deprecation")
    private void setAfterLogin(HttpServletRequest request, HttpServletResponse response, RUser rUser) {

        String sessionId = CookieUtils.getCookieValue(request, HEART_LOGIN_CONSTANTS.COOKIE_HEART_SESSION_ID);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = RanGenUtil.generate32Seq();
            CookieUtils.setCookie(response, HEART_LOGIN_CONSTANTS.COOKIE_HEART_SESSION_ID, sessionId, -1,
                    CookieUtils.getDomain(request), "/");
        }
        int expireSeconds = 60 * 30;
        memcachedTemplate.set(sessionId, expireSeconds, rUser.getId());
        memcachedTemplate.set(sessionId + HEART_LOGIN_CONSTANTS.COOKIE_HEART_USER_NICK, expireSeconds * 60,
                rUser.getNick());
    }

}