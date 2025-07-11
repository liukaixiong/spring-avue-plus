package com.liukx.spring.client.handler;

import com.liukx.spring.client.config.props.AVueProperties;
import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.service.IAVueTokenService;
import com.liukx.spring.client.utils.CookiesUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;


public class LoginHandler implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    @Autowired
    private AVueProperties avueProperties;

    @Autowired
    private IAVueTokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return login(request, response);
    }

    private boolean login(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
