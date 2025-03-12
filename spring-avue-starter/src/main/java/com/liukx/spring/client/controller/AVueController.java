package com.liukx.spring.client.controller;

import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.handler.AVueAnnotationHandlerManager;
import com.liukx.spring.client.handler.AVueJsonCacheHandler;
import com.liukx.spring.client.helper.RouteKeyHelper;
import com.liukx.spring.client.model.ConfigMappingModel;
import com.liukx.spring.client.model.HttpResult;
import com.liukx.spring.client.model.LoginModel;
import com.liukx.spring.client.service.IAVueLoginService;
import com.liukx.spring.client.service.IAVueTokenService;
import com.liukx.spring.client.utils.CookiesUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
public class AVueController {
    private final Logger logger = LoggerFactory.getLogger(AVueController.class);
    public static final String DEFAULT_URL = AVueConstants.Path.DEFAULT_URL;
    public static final String DEFAULT_LOGIN_URL = AVueConstants.Path.DEFAULT_LOGIN_URL;
    public static final String COMPONENT_URL = AVueConstants.Path.COMPONENT_URL;
    private static final String HAL_MEDIA_TYPE = "application/hal+json";

    @Autowired(required = false)
    private IAVueLoginService loginService;
    @Autowired(required = false)
    private IAVueTokenService tokenService;
    @Autowired
    private AVueAnnotationHandlerManager handlerManager;
    @Autowired
    private AVueJsonCacheHandler jsonCacheHandler;

    @GetMapping(value = {AVueConstants.Path.INDEX_CRUD_URL, AVueConstants.Path.COMPONENT_ALL_LIST_URL, AVueConstants.Path.INDEX_COMPONENT_URL, AVueConstants.Path.LOGIN_HTML_PATH})
    public String index() {
        return "forward:" + AVueConstants.Path.INDEX_FORWARD_URL;
    }

    // 改为前端跳转 /list -> redirect: { path: '/avue-component', query: { group: 'avueUrlList' } }
//    @GetMapping(value = {AVueConstants.Path.COMPONENT_ALL_LIST_URL})
//    public String allComponent() {
//        return "redirect:" + AVueConstants.Path.INDEX_COMPONENT_URL + "?group=avueUrlList";
//    }

    @RequestMapping(value = DEFAULT_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE,
            HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Object> getDocumentation(
            @RequestBody ConfigMappingModel configMappingModel) {

        if (!checkParams(configMappingModel)) {
            return ResponseEntity.badRequest().build();
        }
        // 非空判断
        String group = configMappingModel.getGroup();

        if (group.contains("?")) {
            group = group.substring(0, group.indexOf("?"));
        }

        Class clazz = RouteKeyHelper.getInstance().get(group);
        if (clazz == null) {
            final Map<String, Object> jsonObject = jsonCacheHandler.getJsonCache(group);
            if (jsonObject != null) {
                logger.info("从静态缓存中获取AVue模版route-key : " + group);
                return ResponseEntity.ok(jsonObject);
            }
            logger.warn("没有找到AVue对应的模版route-key : " + group);
            return ResponseEntity.notFound().build();
        } else {
            Map<String, Map<String, Object>> parse = handlerManager.parse(clazz);
            return ResponseEntity.ok(parse);
        }
    }


    @RequestMapping(value = COMPONENT_URL, method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE,
            HAL_MEDIA_TYPE})
    @ResponseBody
    public ResponseEntity<Object> componentJson(
            @RequestBody ConfigMappingModel configMappingModel) {

        if (!checkParams(configMappingModel)) {
            return ResponseEntity.badRequest().build();
        }
        // 非空判断
        String group = configMappingModel.getGroup();
        final Map<String, Object> jsonObject = jsonCacheHandler.getJsonCache(group);
        if (jsonObject != null) {
            logger.info("从静态缓存中获取AVue模版route-key : " + group);
            return ResponseEntity.ok(jsonObject);
        }
        logger.warn("没有找到AVue对应的模版route-key : " + group);
        return ResponseEntity.notFound().build();
    }

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

    /**
     * 参数校验
     *
     * @param configMappingModel
     * @return
     */
    private boolean checkParams(ConfigMappingModel configMappingModel) {
        if (!StringUtils.hasLength(configMappingModel.getGroup())) {
            logger.warn(" group is null !");
            return false;
        }
        return true;
    }
}
