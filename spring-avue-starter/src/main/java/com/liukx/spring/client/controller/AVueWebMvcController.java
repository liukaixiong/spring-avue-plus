package com.liukx.spring.client.controller;

import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.model.HttpResult;
import com.liukx.spring.client.model.LoginModel;
import com.liukx.spring.client.service.IAVueLoginService;
import com.liukx.spring.client.service.IAVueTokenService;
import com.liukx.spring.client.utils.CookiesUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 *
 *
 * @author liukaixiong
 * @date 2025/7/14 - 14:02
 */
@Controller
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AVueWebMvcController {
    private final Logger logger = LoggerFactory.getLogger(AVueWebMvcController.class);
    @Autowired(required = false)
    private IAVueLoginService loginService;
    @Autowired(required = false)
    private IAVueTokenService tokenService;
    public static final String DEFAULT_LOGIN_URL = AVueConstants.Path.DEFAULT_LOGIN_URL;
    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    @RequestMapping(value = DEFAULT_LOGIN_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE,
            HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Object> login(HttpServletResponse response,
                                        @RequestBody LoginModel loginModel) {
        final boolean result = loginService.login(loginModel);
        logger.info(" username :{},passwrd :{} , result:{}", loginModel.getUsername(), loginModel.getPassword(), result);
        if (result) {
            final String token = tokenService.tokenCreated(loginModel);
            Map<String, Object> tokenMap = new HashMap<>();
            tokenMap.put(AVueConstants.USER_TOKEN_KEY, token);
            CookiesUtil.setCookie(response, AVueConstants.USER_TOKEN_KEY, token, 60 * 60 * 24);
            return ResponseEntity.ok(HttpResult.ok(tokenMap));
        } else {
            return ResponseEntity.ok(HttpResult.error("校验失败!"));
        }
    }

}
