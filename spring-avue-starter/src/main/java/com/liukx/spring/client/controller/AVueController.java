package com.liukx.spring.client.controller;

import com.liukx.spring.client.enums.AVueConstants;
import com.liukx.spring.client.handler.AVueAnnotationHandler;
import com.liukx.spring.client.handler.AVueJsonCacheHandler;
import com.liukx.spring.client.helper.RouteKeyHelper;
import com.liukx.spring.client.model.ConfigMappingModel;
import com.liukx.spring.client.model.HttpResult;
import com.liukx.spring.client.model.LoginModel;
import com.liukx.spring.client.service.IAVueLoginService;
import com.liukx.spring.client.service.IAVueTokenService;
import com.liukx.spring.client.utils.CookiesUtil;
import jakarta.annotation.Resource;
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
    private AVueAnnotationHandler handler;

    @Resource
    private AVueJsonCacheHandler jsonCacheHandler;

    @GetMapping(value = {AVueConstants.Path.INDEX_CRUD_URL, AVueConstants.Path.INDEX_COMPONENT_URL, AVueConstants.Path.LOGIN_HTML_PATH})
    public String index() {
        return "forward:" + AVueConstants.Path.INDEX_FORWARD_URL;
    }

    @GetMapping(value = {AVueConstants.Path.COMPONENT_ALL_LIST_URL})
    public String allComponent() {
        return "redirect:" + AVueConstants.Path.INDEX_COMPONENT_URL + "?group=avueUrlList";
    }

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
            Map<String, Map<String, Object>> parse = handler.parse(clazz);
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

//    private void setCookie(HttpServletResponse response, String token) {
//        // new一个Cookie对象,键值对为参数
//        Cookie cookie = new Cookie("avue-token", token);
//        // tomcat下多应用共享
//        cookie.setPath("/");
//        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
//        try {
//            URLEncoder.encode(token, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        // 单位：秒
//        cookie.setMaxAge(24 * 60 * 60);
//        // 将Cookie添加到Response中,使之生效
//        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
//    }

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
