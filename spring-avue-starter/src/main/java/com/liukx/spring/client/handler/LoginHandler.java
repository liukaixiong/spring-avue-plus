package com.liukx.spring.client.handler;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.service.IAVueTokenService;
import com.liukx.spring.client.utils.CookiesUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoginHandler implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    @Resource
    private AVueProperties avueProperties;

    @Resource
    private IAVueTokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info(request.getRequestURI());

        if (!avueProperties.isEnableLogin()) {
            return true;
        }

        Cookie cookieByName = CookiesUtil.getCookieByName(request, AVueConstants.USER_TOKEN_KEY);

        if (cookieByName != null && StringUtils.hasText(cookieByName.getValue())) {
            if (tokenService.checkToken(cookieByName.getValue())) {
                return true;
            }
        }

        logger.debug("token 校验失败, 返回登录页!");
        response.sendRedirect(AVueConstants.Path.LOGIN_HTML_PATH);
        return false;
    }
}
